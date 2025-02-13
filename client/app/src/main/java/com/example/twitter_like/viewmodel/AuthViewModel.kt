package com.example.twitter_like.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LifecycleOwner
import com.example.twitter_like.data.model.auth.Register
import com.example.twitter_like.data.model.auth.Login
import com.example.twitter_like.data.request.auth.LoginRequest
import com.example.twitter_like.data.request.auth.RegisterRequest
import com.example.twitter_like.network.callback.GenericCallback
import com.example.twitter_like.repositories.AuthRepository


class AuthViewModel(
    private val authRepository: AuthRepository,
) : ViewModel() {
    fun register(registerData: RegisterRequest, callback: GenericCallback<Register>) {
        authRepository.registerUser(registerData, object : GenericCallback<Register> {
            override fun onSuccess(data: Register) {
                callback.onSuccess(data)
            }
            override fun onError(error: String) {
                callback.onError(error)
            }
        })
    }

    fun login(data: LoginRequest, callback: GenericCallback<Login>) {
        this.authRepository.userLogin(data, object : GenericCallback<Login> {
            override fun onSuccess(data: Login) {
                callback.onSuccess(data)
            }
            override fun onError(error: String) {
                callback.onError(error)
            }
        })
    }
}
