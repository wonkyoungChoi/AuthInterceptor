package com.example.data.remote.interceptors

import android.util.Base64
import android.util.Log
import com.example.data.common.wrappers.SessionManager
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.net.HttpURLConnection
import javax.inject.Inject
import javax.inject.Provider

class AuthInterceptorImpl @Inject constructor(
    private val sessionManager: Provider<SessionManager>
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var accessToken = sessionManager.get().getAccessToken()

        val response = chain.proceed(newRequestWithAccessToken(accessToken, request))

        if(response.code == HttpURLConnection.HTTP_UNAUTHORIZED) {
            synchronized(this) {
                val newAccessToken = sessionManager.get().getAccessToken()
                if (newAccessToken != accessToken) {
                    return chain.proceed(newRequestWithAccessToken(newAccessToken, request))
                } else {
                    accessToken = refreshToken()
                    if (accessToken.isNullOrBlank()) {
                        sessionManager.get().logout()
                        return response
                    }
                    response.close()
                    return chain.proceed(newRequestWithAccessToken(accessToken, request))
                }
            }
        }
        return response
    }

    private fun newRequestWithAccessToken(accessToken: String?, request: Request): Request =
        request.newBuilder()
            .apply { if (accessToken != null) {
                var needRefresh = false
                if(request.url.toString() == "REFRESHAPI") needRefresh = true
                if(!needRefresh) {
                    header("Authorization", "Bearer $accessToken")
                }
                else {
                    header("Authorization", basicAuthorization())
                }
            } else {
                header("Authorization", basicAuthorization())
            } }
            .build()

    private fun basicAuthorization(): String {
        val authString= "CLIENT_ID"
        val authEnc = Base64.encodeToString(authString.toByteArray(), Base64.NO_WRAP)
        return "Basic $authEnc"
    }

    private fun refreshToken(): String? {
        val refreshToken: String? = sessionManager.get().getRefreshToken()
        refreshToken?.let {
            return sessionManager.get().refreshToken(it)
        } ?: return null
    }

    private val Response.responseCount: Int
        get() = generateSequence(this) { it.priorResponse }.count()
}