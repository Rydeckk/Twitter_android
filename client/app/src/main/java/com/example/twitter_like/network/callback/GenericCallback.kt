package com.example.twitter_like.network.callback

interface GenericCallback<T> {
    fun onSuccess(data: T)
    fun onError(error: String)
}