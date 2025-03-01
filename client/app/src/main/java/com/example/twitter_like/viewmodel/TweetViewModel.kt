package com.example.twitter_like.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.twitter_like.data.model.tweet.Tweet
import com.example.twitter_like.data.request.comment.CommentRequest
import com.example.twitter_like.data.request.like.UnlikeRequest
import com.example.twitter_like.network.callback.GenericCallback
import com.example.twitter_like.repositories.TweetRepository

class TweetViewModel(
    private val tweetRepository: TweetRepository,
) : ViewModel() {
    fun getAllTweets(callback: GenericCallback<List<Tweet>>) {
        this.tweetRepository.getAllTweets(object : GenericCallback<List<Tweet>> {
            override fun onSuccess(data: List<Tweet>) {
                callback.onSuccess(data)
            }

            override fun onError(error: String) {
                callback.onError(error)
            }
        })
    }

    fun getUserTweets(callback: GenericCallback<List<Tweet>>) {
        this.tweetRepository.getUserTweets(object : GenericCallback<List<Tweet>> {
            override fun onSuccess(data: List<Tweet>) {
                callback.onSuccess(data)
            }

            override fun onError(error: String) {
                callback.onError(error)
            }
        })
    }

    fun getFollowingUsersTweets(callback: GenericCallback<List<Tweet>>) {
        this.tweetRepository.getFollowingUsersTweets(object : GenericCallback<List<Tweet>> {
            override fun onSuccess(data: List<Tweet>) {
                callback.onSuccess(data)
            }

            override fun onError(error: String) {
                callback.onError(error)
            }
        })
    }

    fun getLikesTweets(callback: GenericCallback<List<Tweet>>) {
        this.tweetRepository.getLikesTweets(object : GenericCallback<List<Tweet>> {
            override fun onSuccess(data: List<Tweet>) {
                callback.onSuccess(data)
            }

            override fun onError(error: String) {
                callback.onError(error)
            }
        })
    }

    fun getTweetById(tweetId: String, callback: GenericCallback<Tweet>) {
        this.tweetRepository.getTweetById(tweetId, object : GenericCallback<Tweet> {
            override fun onSuccess(data: Tweet) {
                callback.onSuccess(data)
            }

            override fun onError(error: String) {
                callback.onError(error)
            }
        })
    }

    fun likeTweet(tweetId: String, callback: () -> Unit) {
        tweetRepository.likeTweet(tweetId, object : GenericCallback<Unit> {
            override fun onSuccess(data: Unit) {
                callback()
            }

            override fun onError(error: String) {
                Log.e("Error", "Erreur : $error")
            }
        })
    }

    fun commentTweet(data: CommentRequest, callback: () -> Unit) {
        tweetRepository.commentTweet(data, object : GenericCallback<Unit> {
            override fun onSuccess(data: Unit) {
                callback()
            }

            override fun onError(error: String) {
                Log.e("Error", "Erreur : $error")
            }
        })
    }

    fun unlikeTweet(data: UnlikeRequest, callback: () -> Unit) {
        tweetRepository.unlikeTweet(data, object : GenericCallback<Unit> {
            override fun onSuccess(data: Unit) {
                callback()
            }

            override fun onError(error: String) {
                Log.e("Error", "Erreur : $error")
            }
        })
    }

}