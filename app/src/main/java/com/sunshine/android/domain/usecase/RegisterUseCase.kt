package com.sunshine.android.domain.usecase

import com.sunshine.android.data.network.dto.auth.LoginResponse
import com.sunshine.android.data.network.dto.user.CreateUserRequest
import com.sunshine.android.data.network.dto.user.CreateUserResponse
import com.sunshine.android.data.repository.AuthRepository
import com.sunshine.android.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
) {
    suspend operator fun invoke(
        name: String,
        character: Int,
        str: Int,
        spi: Int,
        pea: Int,
        kno: Int,
    ): Flow<CreateUserResponse> = userRepository.createUser(
        CreateUserRequest(
            accessToken = getAccessTokenUseCase() ?: "",
            name = name,
            gender = "MALE",
            birthDay = "20000101",
            characterType = listOf("A", "B", "C", "D")[character],
            stat = CreateUserRequest.Stat(
                str = str, spi = spi, pea = pea, kno = kno, ableToEndGame = false
            ),
        )
    )
}