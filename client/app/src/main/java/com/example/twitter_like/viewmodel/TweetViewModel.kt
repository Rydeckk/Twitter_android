package com.example.twitter_like.viewmodel

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.twitter_like.data.model.auth.Login
import com.example.twitter_like.data.model.tweet.Tweet
import com.example.twitter_like.network.callback.GenericCallback
import com.example.twitter_like.repositories.TweetRepository

class TweetViewModel(
    private val tweetRepository: TweetRepository,
) : ViewModel() {
    fun fetchGlobalData(callback: GenericCallback<List<Tweet>>) {
        this.tweetRepository.getUserTweets( object : GenericCallback<List<Tweet>> {
            override fun onSuccess(data: List<Tweet>) {
                callback.onSuccess(data)
            }
            override fun onError(error: String) {
                callback.onError(error)
            }
        })
    }
}