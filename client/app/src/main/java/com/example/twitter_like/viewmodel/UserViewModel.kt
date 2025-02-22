package com.example.twitter_like.viewmodel

import androidx.lifecycle.ViewModel
import com.example.twitter_like.data.model.user.User
import com.example.twitter_like.data.request.user.UserApi
import com.example.twitter_like.network.callback.GenericCallback
import com.example.twitter_like.repositories.UserRepository

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun getUserById(userId: String, callback: GenericCallback<User>) {
        this.userRepository.getUserById(userId, object : GenericCallback<User> {
            override fun onSuccess(data: User) {
                callback.onSuccess(data)
            }

            override fun onError(error: String) {
                callback.onError(error)
            }
        })
    }

    fun updateUserById(data: UserApi, userId: String, callback: GenericCallback<Boolean>) {
        this.userRepository.updateUserById(data, userId, object : GenericCallback<Boolean> {
            override fun onSuccess(data: Boolean) {
                callback.onSuccess(data)
            }

            override fun onError(error: String) {
                callback.onError(error)
            }
        })
    }
}