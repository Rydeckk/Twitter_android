package com.example.twitter_like.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    // TODO check for env variable
    private const val BASE_URL = "http://127.0.0.1:3000/"

    private val okHttpClient = OkHttpClient.Builder().build()

    val instance: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
}