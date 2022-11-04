package com.example.domain.repository

import com.example.domain.core.Result
import com.example.domain.models.ui.UserInfoModel


interface UserInfoRepository {

    suspend fun getUserInfo(): Result<UserInfoModel>

}