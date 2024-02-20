package com.sunshine.android.data.network

import com.sunshine.android.data.dto.NetworkResult
import com.sunshine.android.data.dto.QuestResponse
import com.sunshine.android.data.service.QuestService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(
    @Dispatcher(SunDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val service: QuestService,
) : NetworkDataSource {

    override suspend fun getQuest(questId: Int): NetworkResult<QuestResponse> =
        withContext(ioDispatcher) {
            service.getQuest(questId)
        }
}