package com.sunshine.android.domain.usecase

import com.sunshine.android.data.network.dto.user.HomeResponse
import com.sunshine.android.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHomeUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(): Flow<HomeResponse> = userRepository.getHome()
}