package com.example.twitter_like.data.request.tweet

import com.example.twitter_like.network.dto.users_dto.UserDto

data class TweetRequest(
    val content: String
)

data class TweetResponse(
    val content: String,
    val createdAt: String,
    val users: UserDto
)
