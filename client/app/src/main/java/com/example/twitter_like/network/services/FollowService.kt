package com.example.twitter_like.network.services

import com.example.twitter_like.data.request.follow.FollowRequest
import com.example.twitter_like.data.request.follow.UnfollowRequest
import com.example.twitter_like.network.dto.follow_dto.FollowedByDto
import com.example.twitter_like.network.dto.follow_dto.FollowingDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface FollowService {
    @GET("follows/followers/{userId}")
    fun getUserFollowers(
        @Header("Authorization") token: String,
        @Path("userId") userId: String
    ): Call<List<FollowedByDto>>

    @GET("follows/followings/{userId}")
    fun getUserFollowings(
        @Header("Authorization") token: String,
        @Path("userId") userId: String
    ): Call<List<FollowingDto>>

    @POST("follows")
    fun followUser(
        @Header("Authorization") token: String,
        @Body request: FollowRequest
    ): Call<Void>

    @HTTP(method = "DELETE", path = "follows", hasBody = true)
    fun unfollowUser(
        @Header("Authorization") token: String,
        @Body request: UnfollowRequest
    ): Call<Void>

}