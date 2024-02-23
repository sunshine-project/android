package com.sunshine.android.domain.usecase

import com.sunshine.android.data.network.dto.quest.asQuestModel
import com.sunshine.android.data.repository.QuestRepository
import com.sunshine.android.domain.model.QuestModel
import com.sunshine.android.domain.model.QuestType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetQuestListUseCase @Inject constructor(
    private val questRepository: QuestRepository,
) {
    suspend operator fun invoke(): Flow<List<List<QuestModel>>> =
        combine(questRepository.getQuestList()
            .map { responses -> responses.map { it.asQuestModel() }.toList() },
            questRepository.getQuestListCompleted().map { responses ->
                responses.map { it.asQuestModel() }.toList()
            }) { quests, completedQuests ->
            listOf(
                quests.filter { quest -> quest.questType != QuestType.DAILY },
                quests.filter { quest -> quest.questType == QuestType.DAILY },
                completedQuests
            )
        }
}