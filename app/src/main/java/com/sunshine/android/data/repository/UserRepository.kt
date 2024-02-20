package com.sunshine.android.data.repository

import com.sunshine.android.data.dto.HomeResponse
import com.sunshine.android.data.dto.NetworkResult
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUser(userId: Int): Flow<NetworkResult<HomeResponse>>
}