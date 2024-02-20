package com.sunshine.android.data.service

import com.sunshine.android.data.dto.NetworkResult
import com.sunshine.android.data.dto.QuestListResponse
import com.sunshine.android.data.dto.QuestResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface QuestService {
    @GET("quests/uncompleted")
    suspend fun getQuestList(
    ): NetworkResult<QuestListResponse>

    @GET("quests/{questId}")
    suspend fun getQuest(
        @Path("questId") questId: Int
    ): NetworkResult<QuestResponse>

}