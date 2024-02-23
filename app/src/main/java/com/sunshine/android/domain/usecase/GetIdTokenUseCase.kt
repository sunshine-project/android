package com.sunshine.android.domain.usecase

import android.content.Context
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import androidx.credentials.exceptions.NoCredentialException
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.sunshine.android.ui.feature.start.di.OAuthModule
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetIdTokenUseCase @Inject constructor(
    @OAuthModule.Login private val loginRequest: GetCredentialRequest,
    @OAuthModule.Register private val registerRequest: GetCredentialRequest,
    private val credentialManager: CredentialManager,
) {
    // move logic to repository
    suspend operator fun invoke(context: Context): Flow<String?> = flow {
        // login
        try {
            val result = credentialManager.getCredential(
                request = loginRequest,
                context = context,
            )
            val credential = result.credential
            try {
                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                val idToken = googleIdTokenCredential.idToken
                Log.d("start", "Received google id token: $idToken")
                emit(idToken)
            } catch (e: GoogleIdTokenParsingException) {
                Log.e("start", "Received an invalid google id token response", e)
                emit(null)
            }
        } catch (e: GetCredentialException) {
            if (e is NoCredentialException) {
                // register
                try {
                    val result = credentialManager.getCredential(
                        request = registerRequest,
                        context = context,
                    )
                    val credential = result.credential
                    try {
                        val googleIdTokenCredential =
                            GoogleIdTokenCredential.createFrom(credential.data)
                        val idToken = googleIdTokenCredential.idToken
                        Log.d("start", "Received google id token: $idToken")
                        emit(idToken)
                    } catch (e: GoogleIdTokenParsingException) {
                        Log.e("start", "Received an invalid google id token response", e)
                        emit(null)
                    }
                } catch (e: GetCredentialException) {
                    Log.e("start", "Failed to get credential", e)
                    emit(null)
                }
            } else {
                Log.e("start", "Failed to get credential", e)
                emit(null)
            }
        }
    }
}