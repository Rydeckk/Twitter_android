package com.example.twitter_like.data
import com.example.twitter_like.api.RegisterRequest
import com.example.twitter_like.api.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("auth/register")
    fun register(@Body request: RegisterRequest): Call<RegisterResponse>
}