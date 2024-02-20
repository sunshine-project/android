package com.sunshine.android.data.repository

import com.sunshine.android.data.dto.HomeResponse
import com.sunshine.android.data.dto.NetworkResult
import com.sunshine.android.data.dto.QuestResponse
import com.sunshine.android.data.service.QuestService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface QuestRepository {

    suspend fun getQuest(questId: Int): Flow<NetworkResult<QuestResponse>>

}