package com.sunshine.android.data.service

import com.sunshine.android.data.network.dto.user.HomeResponse
import com.sunshine.android.data.network.dto.user.AlbumResponse
import com.sunshine.android.data.network.dto.user.CreateUserRequest
import com.sunshine.android.data.network.dto.user.CreateUserResponse
import com.sunshine.android.data.network.dto.user.JournalResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {

    @GET("users/mypage/journal")
    suspend fun getJournal(): List<JournalResponse>

    @GET("users/mypage/album")
    suspend fun getAlbum(): List<AlbumResponse>

    @GET("users/home")
    suspend fun getHome(): HomeResponse

    @POST("users")
    suspend fun createUser(
        @Body createUserRequest: CreateUserRequest
    ): CreateUserResponse

}