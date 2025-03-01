package com.example.twitter_like.network.dto.conversation_dto

import java.util.Date

data class ConversationDto(
    val id: String,
    val createdAt: Date,
    val conversationsUsers: List<ConversationUserDto>,
    val createdByUserId: String
)
