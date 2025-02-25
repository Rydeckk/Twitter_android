package com.example.twitter_like.viewmodel.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.twitter_like.repositories.ConversationRepository
import com.example.twitter_like.viewmodel.ConversationViewModel

class ConversationViewModelFactory(private val repository: ConversationRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ConversationViewModel::class.java)) {
            return ConversationViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}