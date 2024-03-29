package com.sunshine.android.data.repository

import com.sunshine.android.data.local.datastore.SunPreferencesDataSource
import javax.inject.Inject

internal class PreferencesRepositoryImpl @Inject constructor(private val preferencesDataSource: SunPreferencesDataSource) :
    PreferencesRepository {
    override suspend fun getStoryCompleted(): Boolean = preferencesDataSource.getStoryCompleted()


    override suspend fun getAccessToken(): String? = preferencesDataSource.getAccessToken()

    override suspend fun getRefreshToken(): String? = preferencesDataSource.getRefreshToken()

    override suspend fun updateStoryCompleted(storyCompleted: Boolean) {
        preferencesDataSource.updateStoryCompleted(storyCompleted)
    }

    override suspend fun updateAccessToken(accessToken: String?) {
        preferencesDataSource.updateAccessToken(accessToken)
    }

    override suspend fun updateRefreshToken(refreshToken: String?) {
        preferencesDataSource.updateRefreshToken(refreshToken)
    }

}