package com.sunshine.android.data.di

import com.sunshine.android.data.repository.QuestRepository
import com.sunshine.android.data.repository.QuestRepositoryImpl
import com.sunshine.android.data.repository.UserRepository
import com.sunshine.android.data.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    internal abstract fun bindsQuestRepository(
        questRepository: QuestRepositoryImpl,
    ): QuestRepository

    @Binds
    internal abstract fun bindsUserRepository(
        userRepository: UserRepositoryImpl,
    ): UserRepository

}