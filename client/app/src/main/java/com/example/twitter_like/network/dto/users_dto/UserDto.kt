package com.example.twitter_like.network.dto.users_dto

import java.time.LocalDateTime

data class UserDto(
    val id: String,
    val email: String,
    val username: String,
    val biography: String?,
    val dateOfBirth: LocalDateTime?,
    val createdAt: LocalDateTime,
)
