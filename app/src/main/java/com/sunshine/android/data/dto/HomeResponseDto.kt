package com.sunshine.android.data.dto

import com.sunshine.android.data.model.UserInfo

data class HomeResponseDto(
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
){
    data class Stat(
        val ableToEndGame: Boolean,
        val pea: Int,
        val skl: Int,
        val spi: Int,
        val str: Int
    )
}

fun HomeResponseDto.asDomain() = UserInfo(
    name = name,
    pea = stat.pea,
    spi = stat.spi,
    str = stat.str,
    kno = stat.skl,
    level = level
)