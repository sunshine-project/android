package com.sunshine.android.domain.usecase

import com.sunshine.android.data.local.datastore.di.SunPreferences
import com.sunshine.android.data.repository.PreferencesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPreferenceUseCase @Inject constructor(
    private val preferencesRepository: PreferencesRepository,
) {
    operator fun invoke(): Flow<SunPreferences> = preferencesRepository.preferencesFlow
}