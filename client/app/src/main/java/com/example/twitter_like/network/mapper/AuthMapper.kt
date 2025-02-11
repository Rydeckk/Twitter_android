package com.example.twitter_like.network.mapper

import com.example.twitter_like.data.model.auth.Login
import com.example.twitter_like.network.dto.auth_dto.LoginDto

fun loginDtoToLoginModel(dto: LoginDto): Login {
    return Login(accessToken = dto.accessToken)
}