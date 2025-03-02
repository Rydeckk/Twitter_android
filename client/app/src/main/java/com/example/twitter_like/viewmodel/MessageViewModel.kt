package com.example.twitter_like.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.twitter_like.data.model.message.Message
import com.example.twitter_like.data.request.message.SendMessageRequest
import com.example.twitter_like.network.callback.GenericCallback
import com.example.twitter_like.repositories.MessageRepository

class MessageViewModel(private val messageRepository: MessageRepository, conversationViewModel: ConversationViewModel): ViewModel() {
    private val _messages = MutableLiveData<List<Message>>()
    val messages: LiveData<List<Message>> get() = _messages

    init {
        conversationViewModel.selectedConversation.observeForever { conversation ->
            if (conversation != null) {
                fetchMessages(conversation.id)
            }
        }
    }

    private fun fetchMessages(conversationId: String) {
        messageRepository.getMessages(conversationId, object : GenericCallback<List<Message>> {
            override fun onSuccess(data: List<Message>) {
                _messages.postValue(data)
            }

            override fun onError(error: String) {
                Log.e("MessageViewModel", "Erreur : $error")
            }
        })
    }

    fun connectToConversation(userId: String) {
        messageRepository.connectToConversation(userId) { newMessage ->
            val currentMessages = _messages.value.orEmpty().toMutableList()
            currentMessages.add(newMessage)
            _messages.postValue(currentMessages)
        }
    }

    fun sendMessage(conversationId: String, messageContent: String) {
        val messageData = SendMessageRequest(messageContent, conversationId)
        messageRepository.sendMessage(messageData, object : GenericCallback<Message> {
            override fun onSuccess(data: Message) {
                val currentList = _messages.value ?: emptyList()
                _messages.postValue(currentList + data)
            }

            override fun onError(error: String) {
                Log.d("Error", error)
            }
        })
    }

    override fun onCleared() {
        super.onCleared()
        messageRepository.closeConnection()
    }
}