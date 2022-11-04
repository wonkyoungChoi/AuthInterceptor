package com.example.data.remote.responses

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    var access_token: String?,
    var expires_in: Int,
    var token_type :String,
    var scope : String,        // ": "read write",
    var refresh_token : String
)