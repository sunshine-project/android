package com.sunshine.android.ui.feature.ending

import android.text.BoringLayout
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunshine.android.domain.model.JobModel
import com.sunshine.android.domain.usecase.GetFinalQuestUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EndingViewModel @Inject constructor(
    private val getFinalQuestUseCase: GetFinalQuestUseCase
) : ViewModel() {
    private val _endingIterator = endingResources.iterator()


    private val _uiState = MutableStateFlow(
        EndingUiState(
            currentEnding = _endingIterator.next(), isShowNextButton = false, isLastPage = false
        )
    )
    val uiState = _uiState.asStateFlow()

    init {
        fetchJob()
    }

    private fun fetchJob() {
        viewModelScope.launch {
            getFinalQuestUseCase().onStart {
                _uiState.update { it.copy(loading = true) }
            }.onCompletion {
                _uiState.update { it.copy(loading = false, error = false) }
            }.catch { e ->
                _uiState.update { it.copy(error = true) }
                Log.e("ending", "fetchJob: ", e)
            }.collectLatest { response ->
                _uiState.update { it.copy(job = response) }
            }
        }
    }

    fun nextPageIfAvailable(): Boolean {
        if (!_endingIterator.hasNext()) return false
        _uiState.update {
            it.copy(
                currentEnding = _endingIterator.next(),
                isLastPage = !_endingIterator.hasNext(),
                isShowNextButton = false,
            )
        }
        if (!_endingIterator.hasNext()) {
            viewModelScope.launch {
//                setEndingFinish()
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

data class EndingUiState(
    val currentEnding: EndingResource,
    val isShowNextButton: Boolean,
    val isLastPage: Boolean,
    val job: List<JobModel> = listOf(),
    val loading: Boolean = false,
    val error: Boolean = false,
)