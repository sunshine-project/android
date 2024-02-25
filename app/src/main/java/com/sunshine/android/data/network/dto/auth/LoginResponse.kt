package com.sunshine.android.data.network.dto.auth


data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
    val email: String,
    val isRegistered: Boolean,
)
