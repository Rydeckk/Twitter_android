package com.example.twitter_like.network.services

import com.example.twitter_like.data.request.conversation.ConversationCreateRequest
import com.example.twitter_like.network.dto.conversation_dto.ConversationDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ConversationService {
    @POST("conversations/user")
    fun create_conversation(@Header("Authorization") token: String, @Body request: ConversationCreateRequest): Call<ConversationDto?>

    @GET("conversations/user")
    fun get_conversation(@Header("Authorization") token: String): Call<List<ConversationDto>>

}