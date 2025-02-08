package com.example.twitter_like.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.twitter_like.data.model.tweet.Tweet
import com.example.twitter_like.repositories.TweetRepository

class TweetViewModel(
    private val tweetRepository: TweetRepository,
    private val context: LifecycleOwner
) : ViewModel() {
    private val _tweetData = MutableLiveData<List<Tweet>>()

    val tweetData: LiveData<List<Tweet>> get() = _tweetData

    fun fetchGlobalData() {
        this.tweetRepository.tweetData.observe(this.context) {
            data -> this@TweetViewModel._tweetData.value = data
        }
        this.tweetRepository.getUserTweets()
    }
}