package com.sunshine.android.domain.usecase

import com.sunshine.android.data.repository.PreferencesRepository
import javax.inject.Inject

class SetRefreshTokenUseCase @Inject constructor(
    private val preferencesRepository: PreferencesRepository,
) {
    suspend operator fun invoke(refreshToken: String?) =
        preferencesRepository.updateRefreshToken(refreshToken)
}