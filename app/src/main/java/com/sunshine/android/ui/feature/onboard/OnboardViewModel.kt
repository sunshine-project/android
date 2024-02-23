package com.sunshine.android.ui.feature.onboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunshine.android.R
import com.sunshine.android.domain.usecase.RegisterUseCase
import com.sunshine.android.util.getString
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
import javax.inject.Inject

@HiltViewModel
class OnboardViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {
    private val _dialogIterator = onboardDialogResources.iterator()

    private val _uiState = MutableStateFlow(
        OnboardUiState(currentDialog = _dialogIterator.next())
    )
    val uiState: StateFlow<OnboardUiState> = _uiState.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    private fun nextDialog() {
        if (!_dialogIterator.hasNext()) return
        _uiState.update {
            it.copy(isShowAnswer = false, currentDialog = _dialogIterator.next())
        }
    }

    fun showAnswer() {
        _uiState.update {
            it.copy(isShowAnswer = true)
        }
    }

    fun nextDialogOrEvent() {
        _error.value = null
        if (uiState.value.currentDialog.event != null) {
            if (uiState.value.currentDialog.event == OnboardEvent.ONBOARD_REGISTER) return register()
            _uiState.update {
                it.copy(currentEvent = uiState.value.currentDialog.event)
            }
            return
        }
        nextDialog()
    }

    fun registerName(name: String) {
        if (name.isNotBlank() && name.length <= 13) {
            _uiState.update {
                it.copy(currentEvent = null, name = name)
            }
            nextDialog()
        } else _error.value = getString(R.string.onboard_error_name)
    }

    fun registerGender(gender: Int) {
        _uiState.update {
            it.copy(currentEvent = null, gender = gender)
        }
        nextDialog()
    }

    fun registerWarning(response: Boolean) {
        _uiState.value.run {
            if (currentWarningPage == 3) {
                _uiState.update {
                    it.copy(currentEvent = null)
                }
                nextDialog()
                if (warning < 2) nextDialog()
            } else {
                _uiState.update {
                    it.copy(
                        currentWarningPage = currentWarningPage + 1,
                        warning = if (!response) warning + 1 else warning
                    )
                }
            }
        }
    }

    fun registerStat(response: Int) {
        _uiState.value.run {
            _uiState.update {
                it.copy(stat = stat.mapIndexed { index, i ->
                    if (index == currentStatPage) when (response) {
                        0, 1, 2 -> 2
                        else -> 1
                    } else i
                })
            }
            if (currentStatPage == 3) {
                _uiState.update {
                    it.copy(currentEvent = null)
                }
                nextDialog()
            } else {
                _uiState.update {
                    it.copy(currentStatPage = currentStatPage + 1)
                }
            }
        }
    }

    private fun register() {
        viewModelScope.launch {
            registerUseCase(
                name = uiState.value.name!!,
                character = uiState.value.gender!!,
                str = uiState.value.stat[0],
                spi = uiState.value.stat[1],
                pea = uiState.value.stat[2],
                kno = uiState.value.stat[3]
            ).onStart {
                _uiState.update {
                    it.copy(loading = true)
                }
            }.onCompletion {
                _uiState.update {
                    it.copy(loading = false)
                }
            }.catch {
                Log.e("register", "register failed: ", it)
            }.collectLatest {
                _uiState.update {
                    it.copy(currentEvent = OnboardEvent.ONBOARD_FINISH)
                }
            }
        }
    }
}

data class OnboardUiState(
    val currentDialog: OnboardDialog,
    val currentEvent: OnboardEvent? = null,
    val isShowAnswer: Boolean = false,
    val name: String? = null,
    val gender: Int? = null,
    val currentWarningPage: Int = 0,
    val warning: Int = 0,
    val currentStatPage: Int = 0,
    val stat: List<Int> = listOf(1, 1, 1, 1),
    val loading: Boolean = false
)