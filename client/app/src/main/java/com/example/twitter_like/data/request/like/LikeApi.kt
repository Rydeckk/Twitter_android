package com.example.twitter_like.data.request.like

data class LikeRequest(
    val tweetId: String
)

data class UnlikeRequest(
    val tweetId: String,
    val likeId: String
)