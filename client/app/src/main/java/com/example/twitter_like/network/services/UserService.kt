package com.example.twitter_like.network.services

import com.example.twitter_like.data.request.user.UserApi
import com.example.twitter_like.network.dto.users_dto.UserDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserService {

    @GET("users/{userId}")
    fun getUserById(
        @Header("Authorization") token: String,
        @Path("userId") userId: String
    ): Call<UserDto>

    @PUT("users/{userId}")
    fun updateUserById(
        @Header("Authorization") token: String,
        @Path("userId") userId: String,
        @Body request: UserApi
    ): Call<UserApi>
}