package com.sunshine.android.data.repository

import com.sunshine.android.data.network.dto.user.AlbumResponse
import com.sunshine.android.data.network.dto.user.CreateUserRequest
import com.sunshine.android.data.network.dto.user.CreateUserResponse
import com.sunshine.android.data.network.dto.user.HomeResponse
import com.sunshine.android.data.network.dto.user.JournalResponse
import com.sunshine.android.data.service.UserService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userService: UserService) :
    UserRepository {
    override suspend fun getHome() = flow {
        emit(userService.getHome())
    }

    override suspend fun getJournal(): Flow<List<JournalResponse>> =
        flow { emit(userService.getJournal()) }

    override suspend fun getAlbum(): Flow<List<AlbumResponse>> =
        flow { emit(userService.getAlbum()) }

    override suspend fun createUser(createUserRequest: CreateUserRequest): Flow<CreateUserResponse> =
        flow { emit(userService.createUser(createUserRequest)) }

}