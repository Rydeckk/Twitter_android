package com.example.twitter_like.network.mapper

import com.example.twitter_like.data.model.tweet.Tweet
import com.example.twitter_like.network.dto.tweets_dto.TweetDto

fun tweetDtoToTweetModel(dto: TweetDto): Tweet {
    return Tweet(
        id = dto.id,
        content = dto.content,
        users = userDtoToUserModel(dto.users),
        userId = dto.userId,
        createdAt = dto.createdAt,
        tweetComments = dto.tweetComments,
        tweetRetweets = dto.tweetRetweets,
        like = dto.like,
        comment = dto.comment,
        retweet = dto.retweet
    )
}