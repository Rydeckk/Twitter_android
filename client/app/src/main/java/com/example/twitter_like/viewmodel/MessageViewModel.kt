package com.example.twitter_like.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.twitter_like.data.model.message.Message
import com.example.twitter_like.network.callback.GenericCallback
import com.example.twitter_like.repositories.MessageRepository

class MessageViewModel(private val messageRepository: MessageRepository, private val conversationViewModel: ConversationViewModel): ViewModel() {
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
}