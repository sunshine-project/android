package com.sunshine.android.ui.feature.ending

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.sunshine.android.domain.model.JobModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EndingViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

//    private val _stateFlow: MutableStateFlow<EndingState> = MutableStateFlow(EndingState())
//
//    val stateFlow: StateFlow<EndingState> = _stateFlow.asStateFlow()


}

data class EndingState(
    val jobModel: JobModel
)