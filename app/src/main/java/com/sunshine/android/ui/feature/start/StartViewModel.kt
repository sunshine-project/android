package com.sunshine.android.ui.feature.start

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunshine.android.domain.usecase.GetAccessTokenUseCase
import com.sunshine.android.domain.usecase.GetHomeUseCase
import com.sunshine.android.domain.usecase.GetIdTokenUseCase
import com.sunshine.android.domain.usecase.GetRefreshTokenUseCase
import com.sunshine.android.domain.usecase.LoginUseCase
import com.sunshine.android.domain.usecase.ReissueUseCase
import com.sunshine.android.domain.usecase.SetAccessTokenUseCase
import com.sunshine.android.domain.usecase.SetRefreshTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val getRefreshTokenUseCase: GetRefreshTokenUseCase,
    private val setAccessTokenUseCase: SetAccessTokenUseCase,
    private val setRefreshTokenUseCase: SetRefreshTokenUseCase,
    private val reissueUseCase: ReissueUseCase,
    private val getHomeUseCase: GetHomeUseCase,
    private val getIdTokenUseCase: GetIdTokenUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<StartUiState> = MutableStateFlow(StartUiState())
    val uiState: StateFlow<StartUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            tryLogin()
        }
    }

    fun googleLogin(context: Context) {
        viewModelScope.launch {
            getIdTokenUseCase(context).collectLatest { idToken ->
                if (idToken == null) {
                    _uiState.update {
                        it.copy(
                            isRegistered = null,
                            error = "Login failed. Please try again.",
                            loading = false
                        )
                    }
                } else {
                    loginUseCase(idToken).onStart {
                        _uiState.update { it.copy(loading = true) }
                    }.onCompletion {
                        _uiState.update { it.copy(loading = false, error = null) }
                    }.catch { e ->
                        _uiState.update { it.copy(error = "Login failed. Please try again.") }
                        Log.e("start", "googleLogin: ", e)
                    }.collectLatest { result ->
                        setAccessTokenUseCase(result.accessToken)
                        setRefreshTokenUseCase(result.refreshToken)
                        _uiState.update { it.copy(isRegistered = result.isRegistered) }
                    }
                }
            }
        }
    }

    private suspend fun tryLogin() {
        getAccessTokenUseCase().let { token ->
            if (token == null) _uiState.update {
                it.copy(
                    isRegistered = null, error = null, loading = false
                )
            }
            else checkUser()
        }
    }

    private suspend fun checkUser() {
        getHomeUseCase().onStart {
            _uiState.update { it.copy(loading = true) }
        }.onCompletion {
            _uiState.update { it.copy(loading = false, error = null) }
        }.catch { e ->
            if (e is HttpException && (e.code() == 404 || e.code() == 403)) _uiState.update {
                it.copy(isRegistered = false)
            }
            else {
                setAccessTokenUseCase(null)
                tryReissue()
            }
        }.collectLatest {
            _uiState.update { it.copy(isRegistered = true) }
        }
    }

    private suspend fun tryReissue() {
        getRefreshTokenUseCase().let { token ->
            if (token == null) _uiState.update {
                it.copy(
                    isRegistered = null, error = null, loading = false
                )
            }
            else reissueUseCase(token).onStart {
                _uiState.update { it.copy(loading = true) }
            }.onCompletion {
                _uiState.update { it.copy(loading = false, error = null) }
            }.catch {
                setRefreshTokenUseCase(null)
                _uiState.update { it.copy(isRegistered = null) }
            }.collectLatest { result ->
                setAccessTokenUseCase(result.accessToken)
                tryLogin()
            }
        }
    }
}

data class StartUiState(
    val isRegistered: Boolean? = null,
    val error: String? = null,
    val loading: Boolean = true,
)
