package com.example.twitter_like.viewmodel.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.twitter_like.repositories.MessageRepository
import com.example.twitter_like.viewmodel.ConversationViewModel
import com.example.twitter_like.viewmodel.MessageViewModel

class MessageViewModelFactory(private val messageRepository: MessageRepository, private val conversationViewModel: ConversationViewModel) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MessageViewModel::class.java)) {
            return MessageViewModel(messageRepository, conversationViewModel) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}