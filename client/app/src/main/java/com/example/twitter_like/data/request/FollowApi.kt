package com.example.twitter_like.data.request

data class FollowRequest(
    val followedById: String,
    val followingId: String
)

data class UnfollowRequest(
    val followedById: String,
    val followingId: String
)