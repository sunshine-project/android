package com.sunshine.android.domain.usecase

import com.sunshine.android.data.repository.PreferencesRepository
import javax.inject.Inject

class SetAccessTokenUseCase @Inject constructor(
    private val preferencesRepository: PreferencesRepository,
) {
    suspend operator fun invoke(accessToken: String?) =
        preferencesRepository.updateAccessToken(accessToken)
}