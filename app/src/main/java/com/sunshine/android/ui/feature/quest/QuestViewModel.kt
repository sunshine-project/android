package com.sunshine.android.ui.feature.quest

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunshine.android.domain.model.QuestModel
import com.sunshine.android.domain.model.QuestType
import com.sunshine.android.domain.usecase.FinishQuestPhotoUseCase
import com.sunshine.android.domain.usecase.FinishQuestShortAnswerUseCase
import com.sunshine.android.domain.usecase.FinishQuestUseCase
import com.sunshine.android.domain.usecase.GetQuestUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.sunshine.android.ui.feature.quest.navigation.QuestArgs
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getQuestUseCase: GetQuestUseCase,
    private val finishQuestUseCase: FinishQuestUseCase,
    private val finishQuestShortAnswerUseCase: FinishQuestShortAnswerUseCase,
    private val finishQuestPhotoUseCase: FinishQuestPhotoUseCase
) : ViewModel() {

    private val questArgs: QuestArgs = QuestArgs(savedStateHandle)
    private val questId = questArgs.questId.toInt()

    private val _uiState: MutableStateFlow<QuestUiState> = MutableStateFlow(QuestUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        observeQuest()
    }

    private fun observeQuest() {
        viewModelScope.launch {
            getQuestUseCase(questId).onStart {
                _uiState.update { QuestUiState.Loading }
            }.catch {
                _uiState.update { QuestUiState.Error }
                Log.e("quest", "observeQuest: ", it)
            }.collectLatest { result ->
                _uiState.update { QuestUiState.Success(quest = result) }
            }
        }
    }

    fun updateInputText(inputText: String) {
        _uiState.update {
            when (val currentState = _uiState.value) {
                is QuestUiState.Success -> currentState.copy(inputText = inputText)
                else -> currentState
            }
        }
    }

    fun updatePhoto(photo: Uri) {
        _uiState.update {
            when (val currentState = _uiState.value) {
                is QuestUiState.Success -> currentState.copy(photo = photo)
                else -> currentState
            }
        }
    }

    fun completeQuest(context: Context) {
        if (_uiState.value !is QuestUiState.Success) return
        val state = _uiState.value as QuestUiState.Success
        viewModelScope.launch {
            when (state.quest.questType) {
                QuestType.SHORT_ANSWER -> {
                    finishQuestShortAnswerUseCase(questId, state.inputText).catch {
                        Log.e("quest", "cannot complete quest: ", it)
                    }.collectLatest {
                        _uiState.update { state.copy(isFinish = true) }
                    }
                }

                QuestType.PHOTO -> {
                    finishQuestPhotoUseCase(
                        questId,
                        photo = state.photo!!,
                        context = context
                    ).catch {
                        Log.e("quest", "cannot complete quest: ", it)
                    }.collectLatest {
                        _uiState.update { state.copy(isFinish = true) }
                    }
                }

                else -> {
                    finishQuestUseCase(questId).catch {
                        Log.e("quest", "cannot complete quest: ", it)
                    }.collectLatest {
                        _uiState.update { state.copy(isFinish = true) }
                    }
                }
            }
        }
    }
}

sealed interface QuestUiState {
    data class Success(
        val quest: QuestModel,
        val inputText: String = "",
        val photo: Uri? = null,
        val isFinish: Boolean = false
    ) : QuestUiState

    data object Error : QuestUiState
    data object Loading : QuestUiState
}
