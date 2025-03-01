package com.example.twitter_like.data.request.message

data class SendMessageRequest(
    val message: String,
    val conversationId: String
)
