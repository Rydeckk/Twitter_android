package com.example.twitter_like.data.request.message

data class SendMessageRequest(
    val content: String,
    val conversationId: String
)
