package com.sunshine.android.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.sunshine.android.data.local.datastore.di.SunPreferences
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject


class SunPreferencesDataSource @Inject constructor(
    private val sunPreferences: DataStore<Preferences>,
) {
    val prefData = sunPreferences.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { preferences ->
        mapPreferences(preferences)
    }


    suspend fun updateStoryCompleted(storyCompleted: Boolean) {
        sunPreferences.edit { preferences ->
            preferences[PreferencesKeys.STORY_COMPLETED] = storyCompleted
        }
    }

    private object PreferencesKeys {
        val STORY_COMPLETED = booleanPreferencesKey("story_completed")
    }

    private fun mapPreferences(preferences: Preferences): SunPreferences {
        val storyCompleted = preferences[PreferencesKeys.STORY_COMPLETED] ?: false
        return SunPreferences(storyCompleted = storyCompleted)
    }
}