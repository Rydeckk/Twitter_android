package com.example.twitter_like.data.request.follow

data class FollowRequest(
    val followingId: String
)

data class UnfollowRequest(
    val followingId: String
)