package com.example.twitter_like.viewmodel.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.twitter_like.repositories.FollowRepository
import com.example.twitter_like.viewmodel.FollowViewModel

class FollowViewModelFactory(private val repository: FollowRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FollowViewModel::class.java)) {
            return FollowViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}