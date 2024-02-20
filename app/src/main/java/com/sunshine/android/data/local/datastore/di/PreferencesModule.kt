package com.sunshine.android.data.local.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.sunshine.android.data.repository.PreferencesRepository
import com.sunshine.android.data.repository.PreferencesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val SUNSHINE_PREFERENCES_NAME = "sunshine_preferences"
val Context.dataStore by preferencesDataStore(name = SUNSHINE_PREFERENCES_NAME)

data class SunPreferences(
    val storyCompleted: Boolean,
)

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferencesModule {

    @Binds
    internal abstract fun bindsPreferenceRepository(
        preferenceRepository: PreferencesRepositoryImpl,
    ): PreferencesRepository

    companion object {
        @Provides
        @Singleton
        fun provideUserDataStorePreferences(
            @ApplicationContext applicationContext: Context
        ): DataStore<Preferences> {
            return applicationContext.dataStore
        }
    }
}