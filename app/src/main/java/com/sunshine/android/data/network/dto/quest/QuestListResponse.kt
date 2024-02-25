package com.sunshine.android.data.network.dto.quest

import com.sunshine.android.domain.model.QuestModel
import com.sunshine.android.domain.model.QuestType

data class QuestListResponse(
    val createdAt: String,
    val updatedAt: String,
    val userQuestId: Int,
    val questTemplate: QuestTemplate,
    val photoUrl: String,
    val shortAnswer: String,
    val completed: Boolean,
    val uncompleted: Boolean,
) {
    data class QuestTemplate(
        val id: Int,
        val questionDay: Int,
        val title: String,
        val description: String,
        val experiencePoint: ExperiencePoint,
        val questionType: String,
        val statInfo: StatInfo,
        val timeLimit: Int,
        val shortAnswerQuest: Boolean,
        val photoQuest: Boolean,
    )

    data class ExperiencePoint(
        val experiencePoint: Int,
    )

    data class StatInfo(
        val statType: String,
        val statValue: Int,
    )
}

fun QuestListResponse.asQuestModel() = QuestModel(
    id = userQuestId,
    title = questTemplate.title,
    description = questTemplate.description,
//    photoUrl = photoUrl,
    exp = questTemplate.experiencePoint.experiencePoint,
    statType = questTemplate.statInfo.statType,
    statValue = questTemplate.statInfo.statValue,
    questType = when (questTemplate.questionType) {
        "SHORT_ANSWER" -> QuestType.SHORT_ANSWER
        "PHOTO" -> QuestType.PHOTO
        "ROUTINE" -> QuestType.ROUTINE
        "TIMER" -> QuestType.TIMER
        "DAILY" -> QuestType.DAILY
        else -> QuestType.SHORT_ANSWER
    },
)