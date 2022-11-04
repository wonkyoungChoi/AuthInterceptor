package com.example.data.remote.services.auth

import com.example.data.remote.requests.RefreshTokenBody
import com.example.data.remote.responses.AuthResponse
import com.example.domain.core.Result
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {

    //suspend fun register(body: RegisterBody): BasicApiResponse<AuthResponse>

    @FormUrlEncoded
    @POST("oauth2/token/")
    suspend fun login(@Field("username") username: String, @Field("password") password: String, @Field("grant_type") grant_type: String): Response<AuthResponse>

    @FormUrlEncoded
    @POST("oauth2/token/")
    suspend fun refreshToken(@Field("refresh_token") refreshToken: String, @Field("grant_type") grant_type: String): Response<AuthResponse>
}
