package com.example.domain.repository

import com.example.domain.models.requests.LoginRequest
import com.example.domain.core.Result

interface AuthRepository {

    suspend fun login(loginRequest: LoginRequest): Result<Unit>

    suspend fun refreshToken(refreshToken: String): Result<Unit>
}