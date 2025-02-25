package com.example.twitter_like.network.dto.conversation_dto

import com.example.twitter_like.network.dto.users_dto.UserDto

data class ConversationUserDto(
    val id: String,
    val userId: String,
    val conversationId: String,
    val users: UserDto
)
