package com.example.twitter_like.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

// Objets utilisés dans les requêtes et les réponses
data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String
)

// Réponse du serveur
data class RegisterResponse(
    val message: String
)
