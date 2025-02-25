package com.example.twitter_like.network.dto.message_dto

import com.example.twitter_like.network.dto.users_dto.UserDto
import java.util.Date

data class MessageDto(
    val id: String,
    val message: String,
    val createdAt: Date,
    val updatedAt: Date,
    val userId: String,
    val users: UserDto,
    val conversationId: String
)
