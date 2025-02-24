package com.example.twitter_like.data.model.tweet

import com.example.twitter_like.network.dto.users_dto.UserDto
import java.time.LocalDateTime

data class Tweet(
    val content: String,
    val createdAt: String,
    val users: UserDto,
    val userId: String,
    val commentCount: Int,
    val retweetCount: Int,
    val likeCount: Int
)
