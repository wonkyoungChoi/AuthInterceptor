package com.example.domain.models.requests

data class LoginRequest(
    val username: String,
    val password: String,
    val grant_type: String
)