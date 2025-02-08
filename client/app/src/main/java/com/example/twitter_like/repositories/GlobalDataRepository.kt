package com.example.twitter_like.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.twitter_like.data.model.GlobalDataModel
import com.example.twitter_like.network.RetrofitClient
import com.example.twitter_like.network.dto.GlobalModelDto
import com.example.twitter_like.network.mapper.mapGlobalDataDtoToGlobalDataModel
import com.example.twitter_like.network.services.GlobalDataService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GlobalDataRepository {
    private val globalDataService = RetrofitClient.instance.create(GlobalDataService::class.java)

    private val _globalData = MutableLiveData<GlobalDataModel>()

    val globalData: LiveData<GlobalDataModel> get() = _globalData

    // TODO get token from shared preferences
    private val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxYjQwZDVhMi1iZDUzLTQyZWMtODIzNy1kNWEzNDYyYTMyYzUiLCJlbWFpbCI6Im1pY2hhZWwuYnJvd25AZXhhbXBsZS5jb20iLCJpYXQiOjE3Mzg5NDU0NDMsImV4cCI6NDg5NDcwNTQ0M30.zyeb_pTqqv0j0QcAzvWDtrcgCkCZpsz3Tg59R1CgwIk"

    fun getAllData() {
        val call = globalDataService.getAllUserTweets(token)

        call.enqueue(object : Callback<GlobalModelDto>{
            override fun onResponse(
                call: Call<GlobalModelDto>,
                response: Response<GlobalModelDto>
            ) {
                val response = response.body()
                _globalData.value = response?.let {
                    mapGlobalDataDtoToGlobalDataModel(it)
                }

            }
            override fun onFailure(call: Call<GlobalModelDto>, t: Throwable) {
                Log.d("Error network", t.message ?: "Error HTTP")
            }

        })
    }

}