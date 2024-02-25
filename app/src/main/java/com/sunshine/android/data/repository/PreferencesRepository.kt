package com.sunshine.android.data.repository

import com.sunshine.android.data.local.datastore.di.SunPreferences
import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {
    suspend fun getStoryCompleted(): Boolean

    suspend fun getAccessToken(): String?

    suspend fun getRefreshToken(): String?
    suspend fun updateStoryCompleted(storyCompleted: Boolean)

    suspend fun updateAccessToken(accessToken: String?)

    suspend fun updateRefreshToken(refreshToken: String?)

}