package com.example.twitter_like.network.services

import com.example.twitter_like.data.request.message.SendMessageRequest
import com.example.twitter_like.network.dto.message_dto.MessageDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface MessageService {
        @POST("messages")
        fun send_message(@Header("Authorization") token: String, @Body request: SendMessageRequest): Call<MessageDto?>

        @GET("messages/{id}")
        fun get_message(@Header("Authorization") token: String, @Path("id") conversationId: String): Call<List<MessageDto>>
}