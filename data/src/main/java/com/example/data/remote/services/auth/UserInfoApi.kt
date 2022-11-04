package com.example.data.remote.services.auth

import com.example.data.remote.requests.RefreshTokenBody
import com.example.data.remote.responses.AuthResponse
import com.example.data.remote.responses.UserInfoResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface UserInfoApi {
    @GET("v1/user/info")
    suspend fun getUserInfo(): Response<UserInfoResponse>
}
