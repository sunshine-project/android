package com.sunshine.android.domain.usecase

import com.sunshine.android.data.network.dto.user.HomeResponse
import com.sunshine.android.data.network.dto.user.JournalResponse
import com.sunshine.android.data.network.dto.user.QuestFinalResponse
import com.sunshine.android.data.network.dto.user.asJobModel
import com.sunshine.android.data.network.dto.user.asJournalModel
import com.sunshine.android.data.repository.QuestRepository
import com.sunshine.android.data.repository.UserRepository
import com.sunshine.android.domain.model.JobModel
import com.sunshine.android.domain.model.JournalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFinalQuestUseCase @Inject constructor(
    private val questRepository: QuestRepository,
) {
    suspend operator fun invoke(): Flow<List<JobModel>> =
        questRepository.getFinalQuest().map { quests ->
            quests.map { it.asJobModel() }
        }
}