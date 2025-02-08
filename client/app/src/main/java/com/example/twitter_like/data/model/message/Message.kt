package com.example.twitter_like.data.model.message

data class Message(
    val id: Int,
    val author: String,
    val content: String,
    val timestamp: Long,
    val conversationId: Int
)
