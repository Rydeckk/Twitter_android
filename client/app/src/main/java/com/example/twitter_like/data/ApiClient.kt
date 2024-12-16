package com.example.twitter_like.api

import com.example.twitter_like.data.AuthService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    private const val BASE_URL = "http://10.0.2.2:3000/"
    // Instance d'API qui sera initialisée uniquement lorsque nécessaire
    val api: AuthService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            // Convertisseur Gson pour transformer les objets JSON en objets Kotlin et vice-versa
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthService::class.java)
    }
}



