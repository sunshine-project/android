package com.sunshine.android.data.network

import com.sunshine.android.data.dto.NetworkResult
import com.sunshine.android.data.dto.QuestResponse

interface NetworkDataSource {
    suspend fun getQuest(questId: Int): NetworkResult<QuestResponse>

}