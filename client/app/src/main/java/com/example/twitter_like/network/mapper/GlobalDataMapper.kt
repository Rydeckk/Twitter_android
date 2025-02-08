package com.example.twitter_like.network.mapper

import com.example.twitter_like.data.model.GlobalDataModel
import com.example.twitter_like.network.dto.GlobalModelDto

fun mapGlobalDataDtoToGlobalDataModel(dto: GlobalModelDto): GlobalDataModel {
    return GlobalDataModel(
        tweets = dto.tweets.map { tweetDtoToTweetModel(it) }
    )
}