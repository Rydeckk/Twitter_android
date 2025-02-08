package com.example.twitter_like.network.services

import com.example.twitter_like.network.dto.tweets_dto.TweetDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface TweetService {
    @GET("tweets/user")
    fun getUserTweets(@Header("Authorization") token: String): Call<List<TweetDto>>
}