package com.example.twitter_like.network.dto.tweets_dto

import com.example.twitter_like.network.dto.users_dto.UserDto

data class TweetDto(
    val content: String,
    val users : UserDto,
    val userId: String,
    val createdAt: String,
    val commentCount: Int = 0,
    val retweetCount:Int = 0,
    val likeCount:Int = 0
)