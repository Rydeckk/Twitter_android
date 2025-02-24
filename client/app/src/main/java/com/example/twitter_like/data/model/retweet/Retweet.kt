package com.example.twitter_like.data.model.retweet

enum class RetweetType {
    REPLY, REPOST
}

data class Retweet(
    val id: String,
    val createdAt: String,
    val updatedAt: String,
    val userId: String,
    val tweetId: String,
    val parentTweetId: String,
    val type: RetweetType,
)
