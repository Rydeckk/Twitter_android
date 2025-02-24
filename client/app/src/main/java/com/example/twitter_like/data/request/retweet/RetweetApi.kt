package com.example.twitter_like.data.request.retweet

import com.example.twitter_like.data.model.retweet.RetweetType

data class RetweetRequest(
    val content: String,
    val type: RetweetType,
    val parentTweetId: String
)
