package com.example.twitter_like.repositories

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.twitter_like.data.model.message.Message
import com.example.twitter_like.data.request.message.SendMessageRequest
import com.example.twitter_like.network.RetrofitClient
import com.example.twitter_like.network.WebSocketManager
import com.example.twitter_like.network.callback.GenericCallback
import com.example.twitter_like.network.dto.message_dto.MessageDto
import com.example.twitter_like.network.mapper.messageDtoToMesageModel
import com.example.twitter_like.network.services.MessageService
import com.example.twitter_like.utils.parseJsonToMessage
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MessageRepository(private val context: Context) {
    private val messageService = RetrofitClient.instance.create(MessageService::class.java)
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MY_APP_SHARED_PREFS", Context.MODE_PRIVATE)
    private val webSocketManager = WebSocketManager()

    private fun getToken(): String? {
        return sharedPreferences.getString("token", null)?.let { "Bearer $it" }
    }

    fun connectToConversation(userId: String, onMessageReceived: (Message) -> Unit) {
        webSocketManager.connect(userId) { messageJson ->
            val message = parseJsonToMessage(messageJson)
            onMessageReceived(message)
        }
    }

    fun sendMessage(messageData: SendMessageRequest, callback: GenericCallback<Message>) {
        val token = getToken() ?: return

        webSocketManager.sendMessage(messageData.conversationId, Gson().toJson(messageData))

        val call = messageService.send_message(token, messageData)
        call.enqueue(object : Callback<MessageDto?> {
            override fun onResponse(call: Call<MessageDto?>, response: Response<MessageDto?>) {
                val message = response.body()
                if (message != null) {
                    callback.onSuccess(messageDtoToMesageModel(message))
                } else {
                    callback.onError("Erreur : ${response.code()}")
                }
            }

            override fun onFailure(call: Call<MessageDto?>, t: Throwable) {
                callback.onError("Erreur réseau : ${t.message}")
            }
        })
    }

    fun getMessages(conversationId: String, callback: GenericCallback<List<Message>>) {
        val token = getToken() ?: return
        val call = messageService.get_message(token, conversationId)
        call.enqueue(object : Callback<List<MessageDto>> {
            override fun onResponse(call: Call<List<MessageDto>>, response: Response<List<MessageDto>>) {
                val bodyResponse = response.body()
                if (bodyResponse?.isNotEmpty() == true) {
                    callback.onSuccess(bodyResponse.map { messageDtoToMesageModel(it) })
                } else {
                    callback.onSuccess(emptyList())
                }
            }

            override fun onFailure(call: Call<List<MessageDto>>, t: Throwable) {
                callback.onError("Erreur réseau : ${t.message}")
            }
        })
    }

    fun closeConnection() {
        webSocketManager.disconnect()
    }
}