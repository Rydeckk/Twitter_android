package com.example.twitter_like.network.dto

import com.example.twitter_like.network.dto.tweets_dto.TweetDto

data class GlobalModelDto(
    val tweets: List<TweetDto>
)