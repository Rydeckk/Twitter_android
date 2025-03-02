package com.example.twitter_like.data.model.retweet

import com.example.twitter_like.data.model.tweet.Tweet
import com.example.twitter_like.data.model.user.User

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
    val users: User,
    val parentTweet: Tweet
)
