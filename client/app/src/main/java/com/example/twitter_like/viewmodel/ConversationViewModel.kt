package com.example.twitter_like.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.twitter_like.data.model.conversation.Conversation
import com.example.twitter_like.data.request.conversation.ConversationCreateRequest
import com.example.twitter_like.network.callback.GenericCallback
import com.example.twitter_like.repositories.ConversationRepository

class ConversationViewModel(private val conversationRepository: ConversationRepository): ViewModel() {
    private val _selectedConversation = MutableLiveData<Conversation?>()
    val selectedConversation: LiveData<Conversation?> = _selectedConversation

    fun getConversation(callback: GenericCallback<List<Conversation>>) {
        this.conversationRepository.getConversation( object : GenericCallback<List<Conversation>> {
            override fun onSuccess(data: List<Conversation>) {
                callback.onSuccess(data)
            }
            override fun onError(error: String) {
                callback.onError(error)
            }
        })
    }

    fun createConversation(createConvData: ConversationCreateRequest,callback: GenericCallback<Conversation>) {
        this.conversationRepository.createConversation( createConvData, object : GenericCallback<Conversation> {
            override fun onSuccess(data: Conversation) {
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
}