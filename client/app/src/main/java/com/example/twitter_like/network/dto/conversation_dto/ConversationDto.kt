package com.example.twitter_like.network.dto.conversation_dto

data class ConversationDto(
    val id: Int,
    val title: String,
    val lastMessage: String,
    val timestamp: Long
)
