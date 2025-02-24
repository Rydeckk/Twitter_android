package com.example.twitter_like.data.request.comment

data class CommentRequest(
    val content: String,
    val parentTweetId: String
)
