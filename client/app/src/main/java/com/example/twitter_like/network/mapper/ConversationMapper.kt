package com.example.twitter_like.network.mapper

import com.example.twitter_like.data.model.conversation.Conversation
import com.example.twitter_like.data.model.conversation.ConversationUser
import com.example.twitter_like.network.dto.conversation_dto.ConversationDto
import com.example.twitter_like.network.dto.conversation_dto.ConversationUserDto

fun conversationDtoToConversationModel(dto: ConversationDto): Conversation {
    return Conversation(
        id = dto.id,
        createdAt = dto.createdAt,
        conversationsUsers = dto.conversationsUsers.map { conversationUserDtoToConversationUserModel(it) },
        createdByUserId = dto.createdByUserId,
    )
}

fun conversationUserDtoToConversationUserModel(dto: ConversationUserDto): ConversationUser {
    return ConversationUser(
        id = dto.id,
        userId = dto.userId,
        conversationId = dto.conversationId,
        users = userDtoToUserModel(dto.users)
    )
}