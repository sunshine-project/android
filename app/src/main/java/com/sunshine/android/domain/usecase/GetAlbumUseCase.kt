package com.sunshine.android.domain.usecase

import com.sunshine.android.data.network.dto.user.AlbumResponse
import com.sunshine.android.data.network.dto.user.HomeResponse
import com.sunshine.android.data.network.dto.user.JournalResponse
import com.sunshine.android.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import javax.inject.Inject

class GetAlbumUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(): Flow<List<String>> = userRepository.getAlbum().map { album ->
            album.filter { it.description != null }
                .map { "https://storage.googleapis.com/sunshine-bucket/" + it.description!! }
        }
}
