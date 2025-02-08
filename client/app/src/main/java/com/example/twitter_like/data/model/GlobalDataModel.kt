package com.example.twitter_like.data.model

import com.example.twitter_like.data.model.conversation.Conversation
import com.example.twitter_like.data.model.tweet.Tweet
import com.example.twitter_like.data.model.user.User

data class GlobalDataModel(
    val tweets: List<Tweet>,
    val user: User,
    val conversations: List<Conversation>
)