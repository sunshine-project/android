package com.sunshine.android.ui.feature.start.di

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.sunshine.android.util.Constants.SERVER_CLIENT_ID
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OAuthModule {
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class Login

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class Register

    @Singleton
    @Login
    @Provides
    fun provideGetGoogleIdOptionFilterTrue(): GetGoogleIdOption =
        GetGoogleIdOption.Builder().setFilterByAuthorizedAccounts(true)
            .setServerClientId(SERVER_CLIENT_ID).setAutoSelectEnabled(true).build()

    @Singleton
    @Register
    @Provides
    fun provideGetGoogleIdOptionFilterFalse(): GetGoogleIdOption =
        GetGoogleIdOption.Builder().setFilterByAuthorizedAccounts(false)
            .setServerClientId(SERVER_CLIENT_ID).setAutoSelectEnabled(true).build()

    @Singleton
    @Login
    @Provides
    fun provideGetCredentialRequestFilterTrue(
        @Login getGoogleIdOption: GetGoogleIdOption
    ): GetCredentialRequest =
        GetCredentialRequest.Builder().addCredentialOption(getGoogleIdOption).build()

    @Singleton
    @Register
    @Provides
    fun provideGetCredentialRequestFilterFalse(
        @Register getGoogleIdOption: GetGoogleIdOption
    ): GetCredentialRequest =
        GetCredentialRequest.Builder().addCredentialOption(getGoogleIdOption).build()

    @Singleton
    @Provides
    fun provideCredentialManager(@ApplicationContext context: Context): CredentialManager =
        CredentialManager.create(context)
}