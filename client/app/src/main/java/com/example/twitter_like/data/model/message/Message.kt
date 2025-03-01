package com.example.twitter_like.data.model.message

import com.example.twitter_like.data.model.user.User
import java.util.Date

data class Message(
    val id: String,
    val message: String,
    val createdAt: Date,
    val updatedAt: Date,
    val userId: String,
    val users: User,
    val conversationId: String
)
