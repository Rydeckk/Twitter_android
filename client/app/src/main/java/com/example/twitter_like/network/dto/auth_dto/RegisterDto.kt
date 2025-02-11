package com.example.twitter_like.network.dto.auth_dto

import com.google.gson.annotations.SerializedName

data class RegisterDto(
    val status: Number
)

data class LoginDto(
    @SerializedName("access_token")
    val accessToken: String?
)
