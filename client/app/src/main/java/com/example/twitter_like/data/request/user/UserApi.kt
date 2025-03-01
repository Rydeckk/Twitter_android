package com.example.twitter_like.data.request.user

data class UpdateUserRequest (
    val firstname: String?,
    val lastname: String?,
    val username: String?,
    val biography: String?
)