package com.sunshine.android.data.network.di

import com.sunshine.android.data.repository.AuthRepository
import com.sunshine.android.data.repository.AuthRepositoryImpl
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
    internal abstract fun bindsAuthRepository(
        authRepository: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    internal abstract fun bindsUserRepository(
        userRepository: UserRepositoryImpl,
    ): UserRepository

    @Binds
    internal abstract fun bindsQuestRepository(
        questRepository: QuestRepositoryImpl,
    ): QuestRepository


}