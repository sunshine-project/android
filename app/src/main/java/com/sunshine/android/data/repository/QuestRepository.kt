package com.sunshine.android.data.repository

import com.sunshine.android.data.network.dto.quest.QuestListResponse
import com.sunshine.android.data.network.dto.quest.QuestResponse
import com.sunshine.android.data.network.dto.user.FinishQuestShortAnswerRequest
import com.sunshine.android.data.network.dto.user.QuestFinalResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

interface QuestRepository {

    suspend fun getQuest(questId: Int): Flow<QuestResponse>

    suspend fun getQuestList(): Flow<List<QuestListResponse>>

    suspend fun getFinalQuest(): Flow<List<QuestFinalResponse>>

    suspend fun getQuestListCompleted(): Flow<List<QuestListResponse>>

    suspend fun finishQuest(questId: Int): Flow<Unit>

    suspend fun finishQuestShortAnswer(
        questId: Int, request: FinishQuestShortAnswerRequest
    ): Flow<Unit>

    suspend fun finishQuestPhoto(
        questId: Int, image: MultipartBody.Part
    ): Flow<Unit>

}