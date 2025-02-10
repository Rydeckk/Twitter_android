package com.example.twitter_like.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.twitter_like.data.model.tweet.Tweet
import com.example.twitter_like.network.RetrofitClient
import com.example.twitter_like.network.dto.tweets_dto.TweetDto
import com.example.twitter_like.network.mapper.tweetDtoToTweetModel
import com.example.twitter_like.network.services.TweetService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TweetRepository {
    private val tweetService = RetrofitClient.instance.create(TweetService::class.java)
    private val _tweetData = MutableLiveData<List<Tweet>>()
    val tweetData: LiveData<List<Tweet>> get() = _tweetData

    // TODO get token from shared preferences
    private val token =
        "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxYjQwZDVhMi1iZDUzLTQyZWMtODIzNy1kNWEzNDYyYTMyYzUiLCJlbWFpbCI6Im1pY2hhZWwuYnJvd25AZXhhbXBsZS5jb20iLCJpYXQiOjE3Mzg5NDU0NDMsImV4cCI6NDg5NDcwNTQ0M30.zyeb_pTqqv0j0QcAzvWDtrcgCkCZpsz3Tg59R1CgwIk"

    fun getUserTweets() {
        val call = tweetService.getUserTweets(token)
        call.enqueue(object : Callback<List<TweetDto>> {
            override fun onResponse(
                call: Call<List<TweetDto>>,
                response: Response<List<TweetDto>>
            ) {
                _tweetData.value = response.body()?.map { tweetDtoToTweetModel(it) } ?: emptyList()
            }

            override fun onFailure(call: Call<List<TweetDto>>, t: Throwable) {
                Log.d("Error network", t.message ?: "Error HTTP")
            }
        })
    }
}