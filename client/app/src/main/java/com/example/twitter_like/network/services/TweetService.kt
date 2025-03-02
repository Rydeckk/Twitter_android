package com.example.twitter_like.network.services

import com.example.twitter_like.data.request.comment.CommentRequest
import com.example.twitter_like.data.request.like.LikeRequest
import com.example.twitter_like.data.request.like.UnlikeRequest
import com.example.twitter_like.data.request.retweet.RetweetRequest
import com.example.twitter_like.data.request.tweet.TweetRequest
import com.example.twitter_like.data.request.tweet.TweetResponse
import com.example.twitter_like.network.dto.tweets_dto.TweetDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface TweetService {

    @GET("tweets")
    fun getAllTweets(@Header("Authorization") token: String): Call<List<TweetDto>>

    @GET("tweets/user/{userId}")
    fun getUserTweets(
        @Header("Authorization") token: String,
        @Path("userId") userId: String
    ): Call<List<TweetDto>>

    @GET("tweets/follow")
    fun getFollowingUsersTweets(@Header("Authorization") token: String): Call<List<TweetDto>>

    @GET("tweets/like/{userId}")
    fun getLikesTweets(
        @Header("Authorization") token: String,
        @Path("userId") userId: String
    ): Call<List<TweetDto>>

    @GET("tweets/retweet/{userId}")
    fun getRetweetsTweets(
        @Header("Authorization") token: String,
        @Path("userId") userId: String
    ): Call<List<TweetDto>>

    @GET("tweets/{userId}")
    fun getTweetById(
        @Header("Authorization") token: String,
        @Path("userId") userId: String
    ): Call<TweetDto>

    @POST("tweets")
    fun sendTweet(
        @Header("Authorization") token: String,
        @Body request: TweetRequest
    ): Call<TweetResponse>

    @POST("likes")
    fun likeTweet(@Header("Authorization") token: String, @Body request: LikeRequest): Call<Void>

    @POST("comments")
    fun commentTweet(
        @Header("Authorization") token: String,
        @Body request: CommentRequest
    ): Call<Void>

    @POST("retweets")
    fun retweetTweet(
        @Header("Authorization") token: String,
        @Body request: RetweetRequest
    ): Call<Void>

    @HTTP(method = "DELETE", path = "likes", hasBody = true)
    fun unlikeTweet(
        @Header("Authorization") token: String,
        @Body request: UnlikeRequest
    ): Call<Void>
}