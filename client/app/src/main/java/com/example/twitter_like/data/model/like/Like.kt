package com.example.twitter_like.data.model.like

data class Like(
    val id: String,
    val createdAt: String,
    val updatedAt: String,
    val userId: String,
    val tweetId: String,
)
