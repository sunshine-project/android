package com.sunshine.android.data.repository

import com.sunshine.android.data.service.UserService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userService: UserService) :
    UserRepository {
    override suspend fun getUser(userId: Int) = flow {
        emit(userService.getUser(userId))
    }

}