package com.sunshine.android.domain.usecase

import com.sunshine.android.data.network.dto.auth.LoginResponse
import com.sunshine.android.data.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(idToken: String): Flow<LoginResponse> =
        authRepository.login(idToken)
}