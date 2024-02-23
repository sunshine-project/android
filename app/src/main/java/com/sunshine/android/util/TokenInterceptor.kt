package com.sunshine.android.util

import com.sunshine.android.domain.usecase.GetAccessTokenUseCase
import com.sunshine.android.domain.usecase.GetRefreshTokenUseCase
import com.sunshine.android.domain.usecase.ReissueUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
) : Interceptor {

    companion object {
        val EXCEPT_LIST = listOf(
            "/login/google", "/reissue", "/users"
        )
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking { getAccessTokenUseCase() }
        val originalRequest = chain.request()
        val request = chain.request().newBuilder().apply {
            if (EXCEPT_LIST.none { originalRequest.url.encodedPath.endsWith(it) }) {
                addHeader("Authorization", "Bearer $token")
            }
        }.build()
        val response = chain.proceed(request)
        if (response.code == 401 && !response.request.url.toString().contains("reissue")) {
            // refresh token
        }
        return response
    }
}