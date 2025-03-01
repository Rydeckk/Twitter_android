package com.example.twitter_like.data.model.follow

import com.example.twitter_like.data.model.user.User

data class Follows(
    val followedById: String,
    val followingId: String,
    val user: User,
    val isUserAlsoFollowing: Boolean? = false
)