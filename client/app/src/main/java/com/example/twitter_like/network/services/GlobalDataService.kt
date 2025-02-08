package com.example.twitter_like.network.services

import com.example.twitter_like.network.dto.GlobalModelDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface GlobalDataService {

    @GET("tweets/user")
    fun getAllUserTweets(@Header("Authorization") token: String): Call<GlobalModelDto>

}