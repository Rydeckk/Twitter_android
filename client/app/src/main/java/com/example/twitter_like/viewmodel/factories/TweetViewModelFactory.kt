package com.example.twitter_like.viewmodel.factories

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.twitter_like.repositories.TweetRepository
import com.example.twitter_like.viewmodel.TweetViewModel

class TweetViewModelFactory(
    private val repository: TweetRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TweetViewModel::class.java)) {
            return TweetViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}