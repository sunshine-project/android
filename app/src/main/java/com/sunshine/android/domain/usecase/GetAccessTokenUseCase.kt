package com.sunshine.android.domain.usecase

import android.util.Log
import com.sunshine.android.data.repository.PreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAccessTokenUseCase @Inject constructor(
    private val preferencesRepository: PreferencesRepository,
) {
    suspend operator fun invoke(): String? {
        val token = preferencesRepository.getAccessToken()
        Log.d("accessToken", "token: $token")
        return token
    }
}