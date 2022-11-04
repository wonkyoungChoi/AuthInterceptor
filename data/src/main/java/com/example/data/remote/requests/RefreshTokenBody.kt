package com.example.data.remote.requests

import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenBody(
    val refreshToken: String,
    val grant_type: String
)
