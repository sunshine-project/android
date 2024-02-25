package com.sunshine.android.domain.model

data class UserModel(
    val name: String,
    val level: Int,
    val str: Int,
    val spi: Int,
    val pea: Int,
    val kno: Int,
    val gender: Int,
    val exp: Int,
    val expLeft: Int,
    val ableToEndGame: Boolean,
)