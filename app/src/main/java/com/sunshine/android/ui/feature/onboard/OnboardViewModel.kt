package com.sunshine.android.ui.feature.onboard

import androidx.lifecycle.ViewModel
import com.sunshine.android.R
import com.sunshine.android.util.getString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class OnboardViewModel @Inject constructor(
) : ViewModel() {
    private val _dialogIterator = onboardDialogResources.iterator()

    private val _onboardUiState = MutableStateFlow(
        OnboardUiState(currentDialog = _dialogIterator.next())
    )
    val onboardUiState: StateFlow<OnboardUiState> = _onboardUiState.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    private fun nextDialog() {
        if (!_dialogIterator.hasNext()) return
        _onboardUiState.update {
            it.copy(isShowAnswer = false, currentDialog = _dialogIterator.next())
        }
    }

    fun showAnswer() {
        _onboardUiState.update {
            it.copy(isShowAnswer = true)
        }
    }

    fun nextDialogOrEvent() {
        _error.value = null
        if (onboardUiState.value.currentDialog.event != null) {
            _onboardUiState.update {
                it.copy(currentEvent = onboardUiState.value.currentDialog.event)
            }
            return
        }
        nextDialog()
    }

    fun registerName(name: String) {
        if (name.isNotBlank() && name.length <= 13) {
            _onboardUiState.update {
                it.copy(currentEvent = null, name = name)
            }
            nextDialog()
        } else _error.value = getString(R.string.onboard_error_name)
    }

    fun registerGender(gender: Int) {
        _onboardUiState.update {
            it.copy(currentEvent = null, gender = gender)
        }
        nextDialog()
    }

    fun registerWarning(response: Boolean) {
        _onboardUiState.value.run {
            if (currentWarningPage == 3) {
                _onboardUiState.update {
                    it.copy(currentEvent = null)
                }
                nextDialog()
                if (warning < 2) nextDialog()
            } else {
                _onboardUiState.update {
                    it.copy(
                        currentWarningPage = currentWarningPage + 1,
                        warning = if (!response) warning + 1 else warning
                    )
                }
            }
        }
    }

    fun registerStat(response: Int) {
        _onboardUiState.value.run {
            _onboardUiState.update {
                it.copy(
                    stat = stat.mapIndexed { index, i ->
                        if (index == currentStatPage) when (response) {
                            0, 1, 2 -> 2
                            else -> 1
                        } else i
                    }
                )
            }
            if (currentStatPage == 3) {
                _onboardUiState.update {
                    it.copy(currentEvent = null)
                }
                nextDialog()
            } else {
                _onboardUiState.update {
                    it.copy(currentStatPage = currentStatPage + 1)
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
)