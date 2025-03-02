package com.example.twitter_like.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.twitter_like.R
import com.example.twitter_like.data.model.conversation.Conversation
import com.example.twitter_like.data.request.conversation.ConversationCreateRequest
import com.example.twitter_like.network.callback.GenericCallback
import com.example.twitter_like.repositories.ConversationRepository

class ConversationViewModel(private val conversationRepository: ConversationRepository): ViewModel() {
    private val _conversations = MutableLiveData<List<Conversation>>()
    val conversations: LiveData<List<Conversation>> = _conversations

    private val _selectedConversation = MutableLiveData<Conversation?>()
    val selectedConversation: LiveData<Conversation?> = _selectedConversation

    fun getConversation() {
        this.conversationRepository.getConversation( object : GenericCallback<List<Conversation>> {
            override fun onSuccess(data: List<Conversation>) {
                _conversations.postValue(data)
            }
            override fun onError(error: String) {
                Log.e("ConversationViewModel", "Erreur : $error")
            }
        })
    }

    fun createConversation(createConvData: ConversationCreateRequest, callback: GenericCallback<Conversation>) {
        this.conversationRepository.createConversation( createConvData, object : GenericCallback<Conversation> {
            override fun onSuccess(data: Conversation) {
                val updatedConversation = _conversations.value?.toMutableList() ?: mutableListOf()
                updatedConversation.add(0, data)
                _conversations.value = updatedConversation
                selectConversation(data)
                callback.onSuccess(data)
            }
            override fun onError(error: String) {
                callback.onError(error)
            }
        })
    }

    fun selectConversation(conversation: Conversation) {
        _selectedConversation.value = conversation
    }

    fun clearSelectedConversation() {
        _selectedConversation.value = null
    }
}