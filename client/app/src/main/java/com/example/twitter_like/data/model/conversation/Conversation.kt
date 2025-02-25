package com.example.twitter_like.data.model.conversation

import java.util.Date

data class Conversation(
    val id: String,
    val createdAt: Date,
    val conversationsUsers: List<ConversationUser>,
    val createdByUserId: String
)
