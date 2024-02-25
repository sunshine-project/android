package com.sunshine.android.domain.usecase

import com.sunshine.android.data.network.dto.auth.ReissueResponse
import com.sunshine.android.data.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReissueUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(refreshToken: String): Flow<ReissueResponse> =
        authRepository.reissueToken(refreshToken)
}