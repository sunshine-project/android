package com.sunshine.android.data.network.dto.quest

import com.sunshine.android.domain.model.QuestModel
import com.sunshine.android.domain.model.QuestType

data class QuestResponse(
    val questionDay: Int,
    val title: String,
    val description: String,
    val experiencePoint: ExperiencePoint,
    val statInfo: StatInfo,
    val questionType: String,
    val timeLimit: Int,
) {
    data class ExperiencePoint(
        val experiencePoint: Int,
    )

    data class StatInfo(
        val statType: String,
        val statValue: Int,
    )
}

fun QuestResponse.asQuestModel() = QuestModel(
    id = 0,
    title = title,
    description = description,
//    photoUrl = photoUrl,
    exp = experiencePoint.experiencePoint,
    statType = statInfo.statType,
    statValue = statInfo.statValue,
    questType = when (questionType) {
        "SHORT_ANSWER" -> QuestType.SHORT_ANSWER
        "PHOTO" -> QuestType.PHOTO
        "ROUTINE" -> QuestType.ROUTINE
        "TIMER" -> QuestType.TIMER
        "DAILY" -> QuestType.DAILY
        else -> QuestType.SHORT_ANSWER
    },
)