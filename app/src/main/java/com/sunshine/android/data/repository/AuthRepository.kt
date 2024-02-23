package com.sunshine.android.data.repository

import com.sunshine.android.data.network.dto.auth.LoginResponse
import com.sunshine.android.data.network.dto.auth.ReissueResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun reissueToken(
        refreshToken: String
    ): Flow<ReissueResponse>

    suspend fun login(idToken: String): Flow<LoginResponse>
}