package com.sunshine.android.domain.usecase

import com.sunshine.android.data.network.dto.quest.asQuestModel
import com.sunshine.android.data.repository.QuestRepository
import com.sunshine.android.domain.model.QuestModel
import com.sunshine.android.domain.model.QuestType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetQuestUseCase @Inject constructor(
    private val questRepository: QuestRepository,
) {
    suspend operator fun invoke(questId: Int): Flow<QuestModel> =
        questRepository.getQuest(questId).map { it.asQuestModel() }
}