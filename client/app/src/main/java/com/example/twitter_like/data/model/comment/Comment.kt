package com.example.twitter_like.data.model.comment

data class Comment(
    val id: String,
    val createdAt: String,
    val updatedAt: String,
    val userId: String,
    val tweetId: String,
    val parentTweetId: String,
)
