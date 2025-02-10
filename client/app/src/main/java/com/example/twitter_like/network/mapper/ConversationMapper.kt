package com.example.twitter_like.network.mapper

import com.example.twitter_like.data.model.conversation.Conversation
import com.example.twitter_like.network.dto.conversation_dto.ConversationDto

fun conversationDtoToConversationModel(dto: ConversationDto): Conversation {
    return Conversation(
        id = dto.id,
        title = dto.title,
        lastMessage = dto.lastMessage,
        timestamp = dto.timestamp
    )
}