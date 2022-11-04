package com.example.data.datasource

import com.example.data.local.AppDataStore
import com.example.data.mappers.toModel
import com.example.data.remote.api_handler.ApiHandler
import com.example.data.remote.services.auth.UserInfoApi
import com.example.domain.core.Result
import com.example.domain.models.ui.UserInfoModel
import com.example.domain.repository.UserInfoRepository
import javax.inject.Inject

class UserInfoRepositoryImpl @Inject constructor(
    private val userInfoApi: UserInfoApi,
    private val appDataStore: AppDataStore,
    private val apiHandler: ApiHandler
) : UserInfoRepository {
    override suspend fun getUserInfo(): Result<UserInfoModel> {
        val response =
            apiHandler.handleCall { userInfoApi.getUserInfo() }


        return response.data?.let { infoResponse ->
            appDataStore.setUserInfo(infoResponse.toModel())
            Result.Success(infoResponse.toModel())
        } ?: Result.Error((response.data?.nickname + response.message))
    }
}