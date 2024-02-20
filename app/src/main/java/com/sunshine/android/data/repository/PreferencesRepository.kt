package com.sunshine.android.data.repository

import com.sunshine.android.data.local.datastore.di.SunPreferences
import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {

    val preferencesFlow: Flow<SunPreferences>
    suspend fun updateStoryCompleted(storyCompleted: Boolean)
}