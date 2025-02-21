package com.example.twitter_like.network.services

import com.example.twitter_like.data.request.conversation.Create
import com.example.twitter_like.network.dto.conversation_dto.ConversationDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ConversationService {
    @POST("conversation/user")
    fun register(@Body request: Create): Call<ConversationDto?>

    @GET("conversations/user")
    fun get_conversation(): Call<ConversationDto?>

}