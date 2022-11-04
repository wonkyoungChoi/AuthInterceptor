package com.example.domain.usecase

import com.example.domain.core.Result
import com.example.domain.models.requests.LoginRequest
import com.example.domain.models.ui.UserInfoModel
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.UserInfoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val userInfoRepository: UserInfoRepository
) {
    suspend operator fun invoke(): Result<UserInfoModel> =
        withContext(Dispatchers.IO) {
            userInfoRepository.getUserInfo()
        }
}