package com.example.twitter_like.data.model.user

import java.time.LocalDateTime

data class User(
    val id: String,
    val email: String,
    val username: String,
    val biography: String?,
    val dateOfBirth: LocalDateTime?,
    val createdAt: LocalDateTime,
)
