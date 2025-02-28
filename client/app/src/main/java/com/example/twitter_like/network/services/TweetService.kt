package com.example.twitter_like.network.services

import com.example.twitter_like.data.model.tweet.Tweet
import com.example.twitter_like.data.request.tweet.LikeRequest
import com.example.twitter_like.data.request.tweet.TweetRequest
import com.example.twitter_like.data.request.tweet.TweetResponse
import com.example.twitter_like.network.dto.tweets_dto.TweetDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface TweetService {

    @GET("tweets")
    fun getAllTweets(@Header("Authorization") token: String): Call<List<TweetDto>>

    @GET("tweets/user")
    fun getUserTweets(@Header("Authorization") token: String): Call<List<TweetDto>>

    @GET("tweets/follow")
    fun getFollowingUsersTweets(@Header("Authorization") token: String): Call<List<TweetDto>>

    @GET("tweets/like")
    fun getLikesTweets(@Header("Authorization") token: String): Call<List<TweetDto>>

    @POST("tweets")
    fun sendTweet(@Header("Authorization") token: String, @Body request: TweetRequest): Call<TweetResponse>

    @POST("likes")
    fun likeTweet(@Header("Authorization") token: String, @Body request: LikeRequest): Call<Void>

}