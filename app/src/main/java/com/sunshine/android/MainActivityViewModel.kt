package com.sunshine.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunshine.android.domain.usecase.GetPreferenceUseCase
import com.sunshine.android.ui.feature.start.navigation.START_GRAPH_ROUTE_PATTERN
import com.sunshine.android.ui.feature.story.navigation.STORY_ROUTE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    getPreference: GetPreferenceUseCase,
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading get() = _isLoading.asStateFlow()

    private val _startDestination = MutableStateFlow<String?>(null)
    val startDestination get() = _startDestination.asStateFlow()

    init {
        viewModelScope.launch {
            delay(2000) // long operation

            val isFinished = getPreference().first().storyCompleted
            _startDestination.value = if (isFinished) {
                START_GRAPH_ROUTE_PATTERN
            } else {
                STORY_ROUTE
            }
            _isLoading.value = false
        }
    }
}