package com.sunshine.android.domain.usecase

import com.sunshine.android.data.repository.PreferencesRepository
import javax.inject.Inject

class SetStoryFinishUseCase @Inject constructor(
    private val preferencesRepository: PreferencesRepository,
) {
    suspend operator fun invoke() = preferencesRepository.updateStoryCompleted(true)
}