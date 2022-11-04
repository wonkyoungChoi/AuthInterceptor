package com.example.data.remote.responses

import kotlinx.serialization.Serializable

@Serializable
data class UserInfoResponse (
    var profile_url: String? = "",   // ": "....",
    var nickname: String = "",     // ": ASDF1234",
    var description : String? = "",     // ": "자기소개",
    var private_id : String = "", //"1234-Q1W2-ER34"
    var email : String = ""      // "test@vlending.co.kr"
)