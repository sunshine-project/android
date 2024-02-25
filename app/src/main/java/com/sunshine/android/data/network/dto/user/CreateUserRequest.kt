package com.sunshine.android.data.network.dto.user


data class CreateUserRequest(
    val accessToken: String,
    val name: String,
    val gender: String,
    val birthDay: String,
    val characterType: String,
    val stat: Stat,
) {
    data class Stat(
        val str: Int,
        val spi: Int,
        val pea: Int,
        val kno: Int,
        val ableToEndGame: Boolean,
    )

}