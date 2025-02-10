package com.example.twitter_like.data.model.user

data class User(
    val id: String,
    val email: String,
    val username: String,
    val biography: String?,
    val dateOfBirth: String?,
    val createdAt: String,
)
