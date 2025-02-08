package com.example.twitter_like.network.dto.tweets_dto

import com.example.twitter_like.network.dto.users_dto.UserDto
import java.time.LocalDateTime

data class TweetDto(
    val content: String,
    val user : UserDto,
    val userId: String,
    val createdAt: LocalDateTime,
)