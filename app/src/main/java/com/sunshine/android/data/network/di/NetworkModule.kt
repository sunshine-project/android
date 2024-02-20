package com.sunshine.android.data.network.di

import com.sunshine.android.data.network.NetworkDataSource
import com.sunshine.android.data.network.NetworkDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface NetworkModule {

    @Binds
    fun binds(impl: NetworkDataSourceImpl): NetworkDataSource
}
