package com.example.data.mappers

import com.example.data.remote.responses.UserInfoResponse
import com.example.domain.models.ui.UserInfoModel

fun UserInfoResponse.toModel(): UserInfoModel =
    UserInfoModel(
        nickname = nickname,
        description = description,
        email = email,
        private_id = private_id,
        profile_url = profile_url
    )