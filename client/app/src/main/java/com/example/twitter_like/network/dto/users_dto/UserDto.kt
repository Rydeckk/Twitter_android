package com.example.twitter_like.network.dto.users_dto

data class UserDto(
    val id: String,
    val email: String,
    val username: String,
    val biography: String?,
    val dateOfBirth: String?,
    val createdAt: String,
)
