package com.sunshine.android.data.repository

import com.sunshine.android.data.dto.HomeResponseDto
import com.sunshine.android.data.dto.NetworkResult
import com.sunshine.android.data.service.UserService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(private val userService: UserService) {
    suspend fun getUser(userId: Int): Flow<NetworkResult<HomeResponseDto>> {
        return flow{
            emit(userService.getUser(userId))
        }
    }
}