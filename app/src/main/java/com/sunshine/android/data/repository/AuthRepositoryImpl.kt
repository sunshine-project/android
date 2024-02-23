package com.sunshine.android.data.repository

import com.sunshine.android.data.network.dto.auth.LoginResponse
import com.sunshine.android.data.network.dto.auth.ReissueResponse
import com.sunshine.android.data.service.AuthService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authService: AuthService) :
    AuthRepository {
    override suspend fun reissueToken(
        refreshToken: String
    ): Flow<ReissueResponse> = flow {
        emit(authService.reissueToken(refreshToken))
    }

    override suspend fun login(idToken: String): Flow<LoginResponse> =
        flow { emit(authService.login(idToken)) }

}
