package com.sunshine.android.data.di

import com.sunshine.android.BuildConfig
import com.sunshine.android.data.repository.QuestRepository
import com.sunshine.android.data.service.QuestService
import com.sunshine.android.data.service.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val BASE_URL = "http://35.216.8.32:8080/api/v1/"

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient.Builder().build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideUserService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

    @Provides
    @Singleton
    fun provideQuestService(retrofit: Retrofit): QuestService {
        return retrofit.create(QuestService::class.java)
    }

//    @Singleton
//    @Provides
//    fun provideUserRepository(apiService: UserService) = UserRepository(apiService)

//    @Singleton
//    @Provides
//    fun provideQuestRepository(apiService: QuestService) = QuestRepository(apiService)
}