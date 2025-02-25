package com.example.twitter_like.repositories

import android.content.Context
import android.content.SharedPreferences
import com.example.twitter_like.data.model.conversation.Conversation
import com.example.twitter_like.data.request.conversation.ConversationCreateRequest
import com.example.twitter_like.network.RetrofitClient
import com.example.twitter_like.network.callback.GenericCallback
import com.example.twitter_like.network.dto.conversation_dto.ConversationDto
import com.example.twitter_like.network.mapper.conversationDtoToConversationModel
import com.example.twitter_like.network.services.ConversationService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConversationRepository(private val context: Context) {
    private val conversationService = RetrofitClient.instance.create(ConversationService::class.java)
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MY_APP_SHARED_PREFS", Context.MODE_PRIVATE)

    private fun getToken(): String? {
        return sharedPreferences.getString("token", null)?.let { "Bearer $it" }
    }

    fun createConversation(createConvData: ConversationCreateRequest, callback: GenericCallback<Conversation>) {
        val token = getToken() ?: return
        val call = conversationService.create_conversation(token, createConvData)
        call.enqueue(object : Callback<ConversationDto?> {
            override fun onResponse(call: Call<ConversationDto?>, response: Response<ConversationDto?>) {
                val conversation = response.body()
                if (conversation != null) {
                    callback.onSuccess(conversationDtoToConversationModel(conversation))
                } else {
                    callback.onError("Erreur : ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ConversationDto?>, t: Throwable) {
                callback.onError("Erreur réseau : ${t.message}")
            }
        })
    }

    fun getConversation(callback: GenericCallback<List<Conversation>>) {
        val token = getToken() ?: return
        val call = conversationService.get_conversation(token)
        call.enqueue(object : Callback<List<ConversationDto>> {
            override fun onResponse(call: Call<List<ConversationDto>>, response: Response<List<ConversationDto>>) {
                val bodyResponse = response.body()
                if (bodyResponse?.isNotEmpty() == true) {
                    callback.onSuccess(bodyResponse.map { conversationDtoToConversationModel(it) })
                } else {
                    callback.onSuccess(emptyList())
                }
            }

            override fun onFailure(call: Call<List<ConversationDto>>, t: Throwable) {
                callback.onError("Erreur réseau : ${t.message}")
            }
        })
    }
}