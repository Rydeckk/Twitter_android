package com.example.twitter_like.network.dto.tweets_dto

import com.example.twitter_like.data.model.comment.Comment
import com.example.twitter_like.data.model.like.Like
import com.example.twitter_like.data.model.retweet.Retweet
import com.example.twitter_like.network.dto.users_dto.UserDto

data class TweetDto(
    val id: String,
    val content: String,
    val users: UserDto,
    val userId: String,
    val createdAt: String,
    val tweetComments: List<Comment>,
    val tweetRetweets: List<Retweet>,
    val like: List<Like>,
    val retweet: Retweet?,
    val comment: Comment?
)