package com.example.twitter_like.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.twitter_like.data.model.follow.Follows
import com.example.twitter_like.data.request.follow.FollowRequest
import com.example.twitter_like.data.request.follow.UnfollowRequest
import com.example.twitter_like.network.callback.GenericCallback
import com.example.twitter_like.repositories.FollowRepository

class FollowViewModel(private val followRepository: FollowRepository) : ViewModel() {

    fun getUserFollowers(userId: String, callback: GenericCallback<List<Follows>>) {
        this.followRepository.getUserFollowers(userId, object : GenericCallback<List<Follows>> {
            override fun onSuccess(data: List<Follows>) {
                callback.onSuccess(data)
            }

            override fun onError(error: String) {
                callback.onError(error)
            }
        })
    }

    fun getUserFollowings(userId: String, callback: GenericCallback<List<Follows>>) {
        this.followRepository.getUserFollowings(userId, object : GenericCallback<List<Follows>> {
            override fun onSuccess(data: List<Follows>) {
                callback.onSuccess(data)
            }

            override fun onError(error: String) {
                callback.onError(error)
            }
        })
    }

    fun followUser(data: FollowRequest, callback: () -> Unit) {
        this.followRepository.followUser(data, object : GenericCallback<Unit> {
            override fun onSuccess(data: Unit) {
                callback()
            }

            override fun onError(error: String) {
                Log.e("Error", "Erreur : $error")
            }
        })
    }

    fun unfollowUser(data: UnfollowRequest, callback: () -> Unit) {
        this.followRepository.unfollowUser(data, object : GenericCallback<Unit> {
            override fun onSuccess(data: Unit) {
                callback()
            }

            override fun onError(error: String) {
                Log.e("Error", "Erreur : $error")
            }
        })
    }
}