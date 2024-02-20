package com.sunshine.android.data.service

import com.sunshine.android.data.dto.HomeResponse
import com.sunshine.android.data.dto.NetworkResult
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {

    @GET("users")
    suspend fun getUser(
        @Query("userId") userId: Int
    ): NetworkResult<HomeResponse>

}