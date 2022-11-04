package com.example.domain.models.ui

data class UserInfoModel (
    var profile_url: String? = "",
    var nickname: String = "",
    var description : String? = "",
    var private_id : String = "",
    var email : String = ""
)
