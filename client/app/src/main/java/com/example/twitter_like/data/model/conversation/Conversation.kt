package com.example.twitter_like.data.model.conversation

data class Conversation(
    val id: Int,
    val title: String,
    val lastMessage: String,
    val timestamp: Long
)
