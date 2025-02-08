package com.example.twitter_like.data.model.tweet

import com.example.twitter_like.network.dto.users_dto.UserDto
import java.time.LocalDateTime

data class Tweet(
    val content: String,
    val createdAt: LocalDateTime,
    val user: UserDto,
    val userId: String
)
