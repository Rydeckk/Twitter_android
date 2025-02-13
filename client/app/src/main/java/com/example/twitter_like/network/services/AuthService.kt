package com.example.twitter_like.network.services


import com.example.twitter_like.data.request.auth.LoginRequest
import com.example.twitter_like.data.request.auth.RegisterRequest
import com.example.twitter_like.network.dto.auth_dto.RegisterDto
import com.example.twitter_like.network.dto.auth_dto.LoginDto
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Body

interface AuthService {
    @POST("auth/register")
    fun register(@Body request: RegisterRequest): Call<RegisterDto?>

    @POST("auth/login")
    fun login(@Body request: LoginRequest): Call<LoginDto?>
}