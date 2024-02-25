package com.sunshine.android.data.repository

import com.sunshine.android.data.network.dto.user.HomeResponse
import com.sunshine.android.data.network.dto.user.AlbumResponse
import com.sunshine.android.data.network.dto.user.CreateUserRequest
import com.sunshine.android.data.network.dto.user.CreateUserResponse
import com.sunshine.android.data.network.dto.user.JournalResponse
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun getJournal(): Flow<List<JournalResponse>>

    suspend fun getAlbum(): Flow<List<AlbumResponse>>

    suspend fun getHome(): Flow<HomeResponse>

    suspend fun createUser(
        createUserRequest: CreateUserRequest
    ): Flow<CreateUserResponse>

}