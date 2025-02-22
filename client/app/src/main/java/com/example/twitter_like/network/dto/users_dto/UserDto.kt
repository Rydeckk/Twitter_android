package com.example.twitter_like.network.dto.users_dto

data class UserDto(
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
