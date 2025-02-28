package com.example.twitter_like.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.twitter_like.data.model.tweet.Tweet
import com.example.twitter_like.network.callback.GenericCallback
import com.example.twitter_like.repositories.TweetRepository

class TweetViewModel(
    private val tweetRepository: TweetRepository,
) : ViewModel() {
    val likedTweets = MutableLiveData<MutableSet<String>>(mutableSetOf())

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

    fun getLikesTweets(callback: GenericCallback<List<Tweet>>) {
        this.tweetRepository.getLikesTweets( object : GenericCallback<List<Tweet>> {
            override fun onSuccess(data: List<Tweet>) {
                callback.onSuccess(data)
            }
            override fun onError(error: String) {
                callback.onError(error)
            }
        })
    }

    fun likeTweet(tweetId: String, token: String) {
        Log.d("LikeDebug", "ViewModel: likeTweet called with id: $tweetId")
        tweetRepository.likeTweet(tweetId, token, object : GenericCallback<Unit> {
            override fun onSuccess(data: Unit) {
                val updatedLikes = likedTweets.value?.toMutableSet() ?: mutableSetOf()
                if (updatedLikes.contains(tweetId)) {
                    updatedLikes.remove(tweetId)
                    Log.d("LikeDebug", "Tweet removed from likes: $tweetId")
                } else {

                    updatedLikes.add(tweetId)
                    Log.d("LikeDebug", "Tweet added to likes: $tweetId")
                }
                likedTweets.postValue(updatedLikes)
            }

            override fun onError(error: String) {
                Log.e("LikeDebug", "Erreur lors du like: $error")
            }
        })
    }



}