package com.sunshine.android.data.repository

import com.sunshine.android.data.local.datastore.SunPreferencesDataSource
import javax.inject.Inject

internal class PreferencesRepositoryImpl @Inject constructor(private val preferencesDataSource: SunPreferencesDataSource) :
    PreferencesRepository {

    override val preferencesFlow = preferencesDataSource.prefData

    override suspend fun updateStoryCompleted(storyCompleted: Boolean) {
        preferencesDataSource.updateStoryCompleted(storyCompleted)
    }

}