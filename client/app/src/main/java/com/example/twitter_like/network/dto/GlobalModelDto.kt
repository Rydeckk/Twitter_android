package com.example.twitter_like.network.dto

import com.example.twitter_like.data.model.user.User
import com.example.twitter_like.network.dto.conversation_dto.ConversationDto
import com.example.twitter_like.network.dto.tweets_dto.TweetDto

data class GlobalModelDto(
    val tweets: List<TweetDto>,
    val user: User,
    val conversations: List<ConversationDto>
)