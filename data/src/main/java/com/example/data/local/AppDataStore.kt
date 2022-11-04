package com.example.data.local

import androidx.datastore.preferences.core.Preferences
import com.example.data.remote.responses.AuthResponse
import com.example.data.remote.responses.UserInfoResponse
import com.example.domain.models.ui.UserInfoModel
import kotlinx.coroutines.flow.Flow
import okhttp3.Response

interface AppDataStore {

    suspend fun putValue(key: Preferences.Key<String>, value: String)

    suspend fun putValue(key: Preferences.Key<Boolean>, value: Boolean)

    fun observeString(key: Preferences.Key<String>) : Flow<String?>

    fun observeBoolean(key: Preferences.Key<Boolean>) : Flow<Boolean>

    suspend fun setTokens(response: AuthResponse?)

    suspend fun removeTokens()

    suspend fun setUserInfo(response: UserInfoModel?)

    companion object {
        const val DATA_STORE_NAME = "DataStore"
    }
}