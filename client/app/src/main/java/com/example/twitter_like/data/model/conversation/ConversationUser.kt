package com.example.twitter_like.data.model.conversation

import com.example.twitter_like.data.model.user.User

data class ConversationUser(
    val id: String,
    val userId: String,
    val conversationId: String,
    val users: User
)
