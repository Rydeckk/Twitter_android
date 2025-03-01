package com.example.twitter_like.network.mapper

import com.example.twitter_like.data.model.follow.Follows
import com.example.twitter_like.network.dto.follow_dto.FollowedByDto
import com.example.twitter_like.network.dto.follow_dto.FollowingDto

fun followingDtoToFollowMapper(dto: FollowingDto): Follows {
    return Follows(
        followingId = dto.followingId,
        followedById = dto.followedById,
        user = userDtoToUserModel(dto.user)
    )
}

fun followedByDtoToFollowMapper(dto: FollowedByDto): Follows {
    return Follows(
        followingId = dto.followingId,
        followedById = dto.followedById,
        user = userDtoToUserModel(dto.user),
        isUserAlsoFollowing = dto.isUserAlsoFollowing
    )
}