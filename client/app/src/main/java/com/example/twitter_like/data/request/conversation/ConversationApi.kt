package com.example.twitter_like.data.request.conversation

data class ConversationCreateRequest(
    val username: Array<String>
)

data class ConversationUpdateRequest(
    val username: Array<String>
)

data class ConversationDeleteRequest(
    val conversationId: String
)