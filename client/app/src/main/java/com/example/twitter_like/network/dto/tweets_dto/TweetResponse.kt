package com.example.twitter_like.network.dto.tweet_dto

import com.example.twitter_like.network.dto.users_dto.UserDto

data class TweetResponse(
    val content: String,
    val createdAt: String,
    val users: UserDto
)
