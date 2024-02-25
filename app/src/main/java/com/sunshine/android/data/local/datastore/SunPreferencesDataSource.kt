package com.sunshine.android.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.sunshine.android.data.local.datastore.di.SunPreferences
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject


class SunPreferencesDataSource @Inject constructor(
    private val sunPreferences: DataStore<Preferences>,
) {
    suspend fun getStoryCompleted(): Boolean {
        return sunPreferences.data.map { preferences ->
            preferences[PreferencesKeys.STORY_COMPLETED] ?: false
        }.first()
    }

    suspend fun getAccessToken(): String? {
        return sunPreferences.data.map { preferences ->
            preferences[PreferencesKeys.ACCESS_TOKEN].takeIf { it?.isNotEmpty() == true }
        }.first()
    }

    suspend fun getRefreshToken(): String? {
        return sunPreferences.data.map { preferences ->
            preferences[PreferencesKeys.REFRESH_TOKEN].takeIf { it?.isNotEmpty() == true }
        }.first()
    }

    suspend fun updateStoryCompleted(storyCompleted: Boolean) {
        sunPreferences.edit { preferences ->
            preferences[PreferencesKeys.STORY_COMPLETED] = storyCompleted
        }
    }

    suspend fun updateAccessToken(accessToken: String?) {
        sunPreferences.edit { preferences ->
            preferences[PreferencesKeys.ACCESS_TOKEN] = accessToken ?: ""
        }
    }

    suspend fun updateRefreshToken(refreshToken: String?) {
        sunPreferences.edit { preferences ->
            preferences[PreferencesKeys.REFRESH_TOKEN] = refreshToken ?: ""
        }
    }

    private object PreferencesKeys {
        val STORY_COMPLETED = booleanPreferencesKey("story_completed")
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
        val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
    }

    private fun mapPreferences(preferences: Preferences): SunPreferences {
        val storyCompleted = preferences[PreferencesKeys.STORY_COMPLETED] ?: false
        val accessToken = preferences[PreferencesKeys.ACCESS_TOKEN]?.takeIf { it.isNotEmpty() }
        val refreshToken = preferences[PreferencesKeys.REFRESH_TOKEN]?.takeIf { it.isNotEmpty() }
        return SunPreferences(
            storyCompleted = storyCompleted, accessToken = accessToken, refreshToken = refreshToken
        )
    }
}