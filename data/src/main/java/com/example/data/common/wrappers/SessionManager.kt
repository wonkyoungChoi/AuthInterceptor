package com.example.data.common.wrappers

import android.util.Log
import com.example.data.local.AppDataStore
import com.example.data.local.DataStoreKeys
import com.example.domain.repository.AuthRepository
import dagger.Lazy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface SessionManager {

    fun getAccessToken(): String?

    fun getRefreshToken(): String?

    fun logout()

    fun refreshToken(refreshToken: String): String?

    suspend fun observeSessionStatus(): Flow<SessionStatus>
}

class SessionManagerImpl @Inject constructor(
    private val appDataStore: AppDataStore,
    private val authRepository: Lazy<AuthRepository>
) : SessionManager {

    override fun getAccessToken(): String? = runBlocking {
        appDataStore.observeString(DataStoreKeys.ACCESS_TOKEN).first()
    }

    override fun getRefreshToken(): String? = runBlocking {
        appDataStore.observeString(DataStoreKeys.REFRESH_TOKEN).first()
    }

    override fun logout() = runBlocking {
        appDataStore.removeTokens()
    }

    override fun refreshToken(refreshToken: String): String? = runBlocking {
        authRepository.get().refreshToken(refreshToken)
        appDataStore.observeString(DataStoreKeys.ACCESS_TOKEN).first()
    }

    override suspend fun observeSessionStatus(): Flow<SessionStatus> = withContext(Dispatchers.IO) {
        appDataStore.observeString(DataStoreKeys.ACCESS_TOKEN)
            .map {
                if (it != null) SessionStatus.LoggedIn else SessionStatus.LoggedOut
            }
            .distinctUntilChanged()
    }
}

enum class SessionStatus {
    LoggedIn, LoggedOut
}