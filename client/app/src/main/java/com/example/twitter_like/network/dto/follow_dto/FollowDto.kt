package com.example.twitter_like.network.dto.follow_dto

import com.example.twitter_like.network.dto.users_dto.UserDto
import com.google.gson.annotations.SerializedName

data class FollowingDto(
    val followedById: String,
    val followingId: String,
    @SerializedName("following")
    val user: UserDto
)

data class FollowedByDto(
    val followedById: String,
    val followingId: String,
    @SerializedName("followedBy")
    val user: UserDto,
    val isUserAlsoFollowing: Boolean? = false
)