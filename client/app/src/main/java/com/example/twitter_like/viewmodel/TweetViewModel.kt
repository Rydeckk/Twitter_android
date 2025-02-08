package com.example.twitter_like.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.twitter_like.data.model.GlobalDataModel
import com.example.twitter_like.repositories.GlobalDataRepository

class TweetViewModel(
    private val globalDataRepo: GlobalDataRepository,
    private val context: LifecycleOwner
) : ViewModel() {
    private val _globalData = MutableLiveData<GlobalDataModel>()

    val globalData: LiveData<GlobalDataModel> get() = _globalData

    fun fetchGlobalData() {
        this.globalDataRepo.globalData.observe(this.context) {
            data -> this@TweetViewModel._globalData.value = data
        }
        this.globalDataRepo.getAllData()
    }
}