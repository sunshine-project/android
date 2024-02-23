package com.sunshine.android.data.repository

import com.sunshine.android.data.network.dto.quest.QuestListResponse
import com.sunshine.android.data.network.dto.quest.QuestResponse
import com.sunshine.android.data.network.dto.user.FinishQuestShortAnswerRequest
import com.sunshine.android.data.network.dto.user.QuestFinalResponse
import com.sunshine.android.data.service.QuestService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import javax.inject.Inject

class QuestRepositoryImpl @Inject constructor(private val questService: QuestService) :
    QuestRepository {
    override suspend fun getQuest(questId: Int): Flow<QuestResponse> =
        flow { emit(questService.getQuest(questId)) }

    override suspend fun getQuestList(): Flow<List<QuestListResponse>> =
        flow { emit(questService.getQuestList()) }

    override suspend fun getFinalQuest(): Flow<List<QuestFinalResponse>> =
        flow { emit(questService.getFinalQuest()) }

    override suspend fun getQuestListCompleted(): Flow<List<QuestListResponse>> =
        flow { emit(questService.getQuestListCompleted()) }

    override suspend fun finishQuest(questId: Int): Flow<Unit> =
        flow { emit(questService.finishQuest(questId)) }

    override suspend fun finishQuestShortAnswer(
        questId: Int, request: FinishQuestShortAnswerRequest
    ): Flow<Unit> = flow { emit(questService.finishQuestShortAnswer(questId, request)) }

    override suspend fun finishQuestPhoto(
        questId: Int, image: MultipartBody.Part
    ): Flow<Unit> = flow { emit(questService.finishQuestPhoto(questId, image)) }

}
