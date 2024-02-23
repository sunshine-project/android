package com.sunshine.android.domain.usecase

import com.sunshine.android.data.network.dto.user.HomeResponse
import com.sunshine.android.data.repository.QuestRepository
import com.sunshine.android.data.repository.UserRepository
import com.sunshine.android.domain.model.QuestType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FinishQuestUseCase @Inject constructor(
    private val questRepository: QuestRepository,
) {
    suspend operator fun invoke(questId: Int) = questRepository.finishQuest(questId)
}