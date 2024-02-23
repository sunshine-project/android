package com.sunshine.android.domain.usecase

import com.sunshine.android.data.network.dto.user.HomeResponse
import com.sunshine.android.data.network.dto.user.JournalResponse
import com.sunshine.android.data.network.dto.user.asJournalModel
import com.sunshine.android.data.repository.UserRepository
import com.sunshine.android.domain.model.JournalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetJournalUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(): Flow<List<JournalModel>> =
        userRepository.getJournal().map { journal ->
            journal.filter { it.description != null }.map { it.asJournalModel() }
        }
}