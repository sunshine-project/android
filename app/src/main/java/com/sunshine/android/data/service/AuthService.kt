package com.sunshine.android.data.service

import com.sunshine.android.data.network.dto.auth.LoginResponse
import com.sunshine.android.data.network.dto.auth.ReissueResponse
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {
    @POST("auth/reissue")
    suspend fun reissueToken(
        @Query("refresh_token") refreshToken: String
    ): ReissueResponse

    @POST("auth/login/google")
    suspend fun login(
        @Query("id_token") idToken: String
    ): LoginResponse

}