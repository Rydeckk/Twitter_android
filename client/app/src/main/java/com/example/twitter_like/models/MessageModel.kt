package com.example.twitter_like.models

data class MessageModel(
    val id: Int,
    val author: String,
    val content: String,
    val timestamp: Long,
    val conversationId: Int
)
