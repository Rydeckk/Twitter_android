package com.example.twitter_like.models

data class ConversationModel(
    val id: Int,
    val title: String,
    val lastMessage: String,
    val timestamp: Long
)
