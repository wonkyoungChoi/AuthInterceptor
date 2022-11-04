package com.example.data.datasource

import android.util.Log
import com.example.data.local.AppDataStore
import com.example.data.remote.api_handler.ApiHandler
import com.example.data.remote.services.auth.AuthApi
import com.example.domain.core.Result
import com.example.domain.models.requests.LoginRequest
import com.example.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val appDataStore: AppDataStore,
    private val apiHandler: ApiHandler
) : AuthRepository {

    override suspend fun login(loginRequest: LoginRequest): Result<Unit> {
        val response =
            apiHandler.handleCall { authApi.login(loginRequest.username, loginRequest.password, loginRequest.grant_type) }

        return response.data?.let { authResponse ->
            appDataStore.setTokens(authResponse)
            Result.Success(Unit)
        } ?: Result.Error((response.data?.access_token + response.message) ?: "")
    }

    override suspend fun refreshToken(refreshToken: String): Result<Unit> {
        val response =
            apiHandler.handleCall { authApi.refreshToken(refreshToken, "refresh_token") }
        return response.data?.let { authResponse ->
            appDataStore.setTokens(authResponse)
            Result.Success(Unit)
        } ?: Result.Error(response.message ?: "")
    }
}