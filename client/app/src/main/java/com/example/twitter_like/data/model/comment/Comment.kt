package com.example.twitter_like.data.model.comment

import com.example.twitter_like.data.model.tweet.Tweet
import com.example.twitter_like.data.model.user.User

data class Comment(
    val id: String,
    val createdAt: String,
    val updatedAt: String,
    val userId: String,
    val tweetId: String,
    val parentTweetId: String,
    val tweet: Tweet,
    val users: User,
    val parentTweet: Tweet
)
