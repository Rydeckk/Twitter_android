package com.example.twitter_like.network.services

import com.example.twitter_like.network.dto.tweets_dto.TweetDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface TweetService {

    @GET("tweets")
    fun getAllTweets(@Header("Authorization") token: String): Call<List<TweetDto>>

    @GET("tweets/user")
    fun getUserTweets(@Header("Authorization") token: String): Call<List<TweetDto>>

    @GET("tweets/follow")
    fun getFollowingUsersTweets(@Header("Authorization") token: String): Call<List<TweetDto>>

    @GET("tweets/like")
    fun getLikesTweets(@Header("Authorization") token: String): Call<List<TweetDto>>
}