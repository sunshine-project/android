package com.sunshine.android.ui.feature.quest

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunshine.android.data.dto.NetworkResult
import com.sunshine.android.data.dto.QuestResponse
import com.sunshine.android.data.dto.asQuestModel
import com.sunshine.android.data.repository.QuestRepository
import com.sunshine.android.domain.model.QuestModel
import com.sunshine.android.domain.model.QuestType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.sunshine.android.ui.feature.quest.navigation.QuestArgs
import com.sunshine.android.ui.feature.story.StoryUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle, private val questRepository: QuestRepository
) : ViewModel() {

    private val questArgs: QuestArgs = QuestArgs(savedStateHandle)
    private val questId = questArgs.questId

    private val _uiState: MutableStateFlow<QuestUiState> = MutableStateFlow(QuestUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getQuest()

    }

    private fun getQuest() {
        _uiState.update {
            QuestUiState.Success(
                quest = QuestModel(
                    title = "5분 간 명상하기",
                    description = "졸린 꾸벅이를 위해 " + "5분 동안 명상하고 마음을 진정시키세요.",
                    exp = 10,
                    statType = "STR",
                    statValue = 1,
                    questType = QuestType.PHOTO,
                ),
            )
        }
        //        viewModelScope.launch {
//            questRepository.getQuest(questId.toInt())
//                .onStart { _uiState.update { QuestUiState.Loading } }
//                .catch { _uiState.update { QuestUiState.Error } }.collectLatest { result ->
//                    when (result) {
//                        is NetworkResult.Success -> {
//                            _uiState.update { QuestUiState.Success(quest = result.value.asQuestModel()) }
//                        }
//
//                        is NetworkResult.Error -> {
//                            _uiState.update { QuestUiState.Error }
//                        }
//
//                        else -> {}
//                    }
//                }
//        }
//    }
    }

    fun updateInputText(inputText: String) {
        _uiState.update {
            when (val currentState = _uiState.value) {
                is QuestUiState.Success -> currentState.copy(inputText = inputText)
                else -> currentState
            }
        }
    }
}

sealed interface QuestUiState {
    data class Success(val quest: QuestModel, val inputText: String = "") : QuestUiState
    data object Error : QuestUiState
    data object Loading : QuestUiState
}