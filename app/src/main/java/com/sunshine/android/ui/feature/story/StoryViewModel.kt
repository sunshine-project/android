package com.sunshine.android.ui.feature.story

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunshine.android.domain.usecase.SetStoryFinishUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoryViewModel @Inject constructor(
    private val setStoryFinish: SetStoryFinishUseCase,
) : ViewModel() {

    private val _storyIterator = storyResources.iterator()


    private val _uiState = MutableStateFlow(
        StoryUiState(
            currentStory = _storyIterator.next(), isShowNextButton = false, isLastPage = false
        )
    )
    val uiState = _uiState.asStateFlow()
    fun nextPageIfAvailable(): Boolean {
        if (!_storyIterator.hasNext()) return false
        _uiState.update {
            it.copy(
                currentStory = _storyIterator.next(),
                isLastPage = !_storyIterator.hasNext(),
                isShowNextButton = false,
            )
        }
        if (!_storyIterator.hasNext()) {
            viewModelScope.launch {
                setStoryFinish()
            }
        }
        return true
    }

    fun showNextButton() {
        _uiState.update {
            it.copy(isShowNextButton = true)
        }
    }
}

data class StoryUiState(
    val currentStory: StoryResource, val isShowNextButton: Boolean, val isLastPage: Boolean
)