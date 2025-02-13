package com.example.twitter_like.repositories

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.twitter_like.data.model.tweet.Tweet
import com.example.twitter_like.network.RetrofitClient
import com.example.twitter_like.network.callback.GenericCallback
import com.example.twitter_like.network.dto.tweets_dto.TweetDto
import com.example.twitter_like.network.mapper.tweetDtoToTweetModel
import com.example.twitter_like.network.services.TweetService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TweetRepository(private val context: Context) {
    private val tweetService = RetrofitClient.instance.create(TweetService::class.java)
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MY_APP_SHARED_PREFS", Context.MODE_PRIVATE)

    private fun getToken(): String? {
        return sharedPreferences.getString("token", null)?.let { "Bearer $it" }
    }

    fun getUserTweets(callback: GenericCallback<List<Tweet>>) {
        val token = getToken()
        Log.d("token", "$token")
        // TODO @Nicolas check if is necessary
        if (token == null) {
            Log.d("TweetRepository", "Token is null, aborting fetchTweets")
            return
        }
        val call = tweetService.getUserTweets(token)
        call.enqueue(object : Callback<List<TweetDto>> {
            override fun onResponse(
                call: Call<List<TweetDto>>,
                response: Response<List<TweetDto>>
            ) {
                val bodyResponse = response.body()
                if (bodyResponse?.isNotEmpty() == true) {
                    callback.onSuccess(bodyResponse.map { tweetDtoToTweetModel(it) })
                } else {
                    callback.onSuccess(emptyList())
                }
            }
            override fun onFailure(call: Call<List<TweetDto>>, t: Throwable) {
                callback.onError("Erreur réseau : ${t.message}")
            }
        })
    }
}