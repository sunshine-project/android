package com.sunshine.android.data.service

import com.sunshine.android.data.dto.HomeResponseDto
import com.sunshine.android.data.dto.NetworkResult
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {

    @GET("api/v1/users") //홈 화면 조회
    suspend fun getUser(
        @Query("userId") userId: Int
    ): NetworkResult<HomeResponseDto>

}