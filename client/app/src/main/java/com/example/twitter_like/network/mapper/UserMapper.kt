package com.example.twitter_like.network.mapper

import com.example.twitter_like.data.model.user.User
import com.example.twitter_like.network.dto.users_dto.UserDto

fun userDtoToUserModel(dto: UserDto): User {
    return User(
        id = dto.id,
        email = dto.email,
        username = dto.username,
        biography = dto.biography,
        dateOfBirth = dto.dateOfBirth,
        createdAt = dto.createdAt
    )
}