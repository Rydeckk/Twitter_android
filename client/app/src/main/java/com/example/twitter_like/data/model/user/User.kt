package com.example.twitter_like.data.model.user

data class User(
    val id: String,
    val email: String,
    val firstname: String?,
    val lastname: String?,
    val username: String,
    val biography: String?,
    val dateOfBirth: String?,
    val createdAt: String,
    val followedByCount: Number?,
    val followingCount: Number?
)
