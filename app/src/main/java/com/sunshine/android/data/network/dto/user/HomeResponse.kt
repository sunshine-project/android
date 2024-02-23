package com.sunshine.android.data.network.dto.user

import com.sunshine.android.domain.model.UserModel

data class HomeResponse(
    val characterType: String,
    val exclusiveRange: Int,
    val experiencePoint: Int,
    val id: Int,
    val isAbleToEndGame: Boolean,
    val leftDay: Int,
    val level: Int,
    val name: String,
    val stat: Stat,
    val uncompletedQuestSize: Int
) {
    data class Stat(
        val ableToEndGame: Boolean,
        val pea: Int,
        val kno: Int,
        val spi: Int,
        val str: Int
    )
}

fun HomeResponse.asUserModel() = UserModel(
    name = name,
    pea = stat.pea,
    spi = stat.spi,
    str = stat.str,
    kno = stat.kno,
    level = level,
    gender = when (characterType) {
        "A" -> 0
        "B" -> 1
        "C" -> 2
        else -> 3
    },
    exp = experiencePoint,
    expLeft = exclusiveRange,
    ableToEndGame = isAbleToEndGame
)