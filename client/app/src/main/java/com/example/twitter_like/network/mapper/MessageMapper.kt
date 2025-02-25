package com.example.twitter_like.network.mapper

import com.example.twitter_like.data.model.message.Message
import com.example.twitter_like.network.dto.message_dto.MessageDto

fun messageDtoToMesageModel(dto: MessageDto): Message {
    return Message(
        id = dto.id,
        message = dto.message,
        createdAt = dto.createdAt,
        updatedAt = dto.updatedAt,
        userId = dto.userId,
        users = userDtoToUserModel(dto.users),
        conversationId = dto.conversationId
    )
}