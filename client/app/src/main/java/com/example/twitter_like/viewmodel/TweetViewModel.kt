package com.example.twitter_like.viewmodel

import androidx.lifecycle.ViewModel
import com.example.twitter_like.data.model.tweet.Tweet
import com.example.twitter_like.network.callback.GenericCallback
import com.example.twitter_like.repositories.TweetRepository

class TweetViewModel(
    private val tweetRepository: TweetRepository,
) : ViewModel() {

    fun getAllTweets(callback: GenericCallback<List<Tweet>>) {
        this.tweetRepository.getAllTweets( object : GenericCallback<List<Tweet>> {
            override fun onSuccess(data: List<Tweet>) {
                callback.onSuccess(data)
            }
            override fun onError(error: String) {
                callback.onError(error)
            }
        })
    }

    fun getUserTweets(callback: GenericCallback<List<Tweet>>) {
        this.tweetRepository.getUserTweets( object : GenericCallback<List<Tweet>> {
            override fun onSuccess(data: List<Tweet>) {
                callback.onSuccess(data)
            }
            override fun onError(error: String) {
                callback.onError(error)
            }
        })
    }

    fun getFollowingUsersTweets(callback: GenericCallback<List<Tweet>>) {
        this.tweetRepository.getFollowingUsersTweets( object : GenericCallback<List<Tweet>> {
            override fun onSuccess(data: List<Tweet>) {
                callback.onSuccess(data)
            }
            override fun onError(error: String) {
                callback.onError(error)
            }
        })
    }
}