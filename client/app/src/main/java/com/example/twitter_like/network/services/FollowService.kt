package com.example.twitter_like.network.services

import com.example.twitter_like.network.dto.follow_dto.FollowedByDto
import com.example.twitter_like.network.dto.follow_dto.FollowingDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface FollowService {
    @GET("follows/followers/{userId}")
    fun getUserFollowers(@Header("Authorization") token: String, @Path("userId") userId: String): Call<List<FollowedByDto>>

    @GET("follows/followings/{userId}")
    fun getUserFollowings(@Header("Authorization") token: String, @Path("userId") userId: String): Call<List<FollowingDto>>

}