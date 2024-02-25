package com.sunshine.android.data.service

import com.sunshine.android.data.network.dto.quest.QuestListResponse
import com.sunshine.android.data.network.dto.quest.QuestResponse
import com.sunshine.android.data.network.dto.user.FinishQuestShortAnswerRequest
import com.sunshine.android.data.network.dto.user.QuestFinalResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface QuestService {

    @GET("quests/{questId}")
    suspend fun getQuest(
        @Path("questId") questId: Int,
    ): QuestResponse

    @GET("quests/uncompleted")
    suspend fun getQuestList(): List<QuestListResponse>

    @GET("quests/final")
    suspend fun getFinalQuest(): List<QuestFinalResponse>

    @GET("quests/completed")
    suspend fun getQuestListCompleted(): List<QuestListResponse>

    @POST("quests/{questId}")
    suspend fun finishQuest(
        @Path("questId") questId: Int,
    )

    @POST("quests/short-answer/{questId}")
    suspend fun finishQuestShortAnswer(
        @Path("questId") questId: Int,
        @Body finishQuestShortAnswerRequest: FinishQuestShortAnswerRequest
    )

    @Multipart
    @POST("quests/photo/{questId}")
    suspend fun finishQuestPhoto(
        @Path("questId") questId: Int,
        @Part photo: MultipartBody.Part,
    )
}