package com.sunshine.android.domain.model

data class QuestModel(
    val id: Int = 0,
    val title: String,
    val description: String,
//    val photoUrl: String,
    val exp: Int,
    val statType: String,
    val statValue: Int,
    val questType: QuestType
)

enum class QuestType {
    SHORT_ANSWER, PHOTO, ROUTINE, TIMER, DAILY
}