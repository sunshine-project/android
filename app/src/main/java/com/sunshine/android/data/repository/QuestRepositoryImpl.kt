package com.sunshine.android.data.repository

import com.sunshine.android.data.dto.NetworkResult
import com.sunshine.android.data.dto.QuestResponse
import com.sunshine.android.data.network.NetworkDataSource
import com.sunshine.android.data.service.QuestService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class QuestRepositoryImpl @Inject constructor(private val questService: QuestService) :
    QuestRepository {
    override suspend fun getQuest(questId: Int): Flow<NetworkResult<QuestResponse>> =
        flow { emit(questService.getQuest(questId)) }

}
