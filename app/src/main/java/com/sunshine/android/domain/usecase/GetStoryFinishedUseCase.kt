package com.sunshine.android.domain.usecase

import com.sunshine.android.data.repository.PreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetStoryFinishedUseCase @Inject constructor(
    private val preferencesRepository: PreferencesRepository,
) {
    suspend operator fun invoke(): Boolean = preferencesRepository.getStoryCompleted()
}