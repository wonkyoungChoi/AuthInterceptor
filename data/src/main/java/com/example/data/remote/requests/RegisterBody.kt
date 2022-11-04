package com.example.data.remote.requests

import kotlinx.serialization.Serializable

@Serializable
data class RegisterBody(
    val username: String,
    val email: String,
    val password: String
)