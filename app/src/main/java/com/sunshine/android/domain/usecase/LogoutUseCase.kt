package com.sunshine.android.domain.usecase

import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import com.sunshine.android.data.network.dto.auth.LoginResponse
import com.sunshine.android.data.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val setAccessTokenUseCase: SetAccessTokenUseCase,
    private val setRefreshTokenUseCase: SetRefreshTokenUseCase,
    private val credentialManager: CredentialManager,
) {
    suspend operator fun invoke() {
        setAccessTokenUseCase(null)
        setRefreshTokenUseCase(null)
        credentialManager.clearCredentialState(ClearCredentialStateRequest())
    }
}