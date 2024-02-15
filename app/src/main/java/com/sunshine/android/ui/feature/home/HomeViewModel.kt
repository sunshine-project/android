package com.sunshine.android.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunshine.android.data.dto.NetworkResult
import com.sunshine.android.data.dto.asDomain
import com.sunshine.android.data.model.UserInfo
import com.sunshine.android.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {

    private val _HomeScreenUiState: MutableStateFlow<State> = MutableStateFlow(State())
    val HomeScreenUiState: StateFlow<State> = _HomeScreenUiState.asStateFlow()


    data class State(
        val loading: Boolean = true,
        val error: Boolean = false,
        val user: UserInfo? = null
    )

    init {
        getHome()
    }

    fun getHome() {
        viewModelScope.launch {
            val result = repository.getUser(1).onStart {
                _HomeScreenUiState.update { state -> state.copy(loading = true) }
            }.catch {
                _HomeScreenUiState.update { state ->
                    state.copy(
                        loading = false,
                        error = true
                    )
                }
            }.collectLatest { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _HomeScreenUiState.update { state ->
                            state.copy(
                                loading = false,
                                user = result.value.asDomain()
                            )
                        }
                    }

                    else -> {}
                }
            }
        }
    }
}