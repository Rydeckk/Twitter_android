package com.example.twitter_like.repositories

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.auth0.android.jwt.JWT
import com.example.twitter_like.data.model.tweet.Tweet
import com.example.twitter_like.data.request.comment.CommentRequest
import com.example.twitter_like.data.request.like.LikeRequest
import com.example.twitter_like.data.request.like.UnlikeRequest
import com.example.twitter_like.data.request.retweet.RetweetRequest
import com.example.twitter_like.data.request.tweet.TweetRequest
import com.example.twitter_like.data.request.tweet.TweetResponse
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
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MY_APP_SHARED_PREFS", Context.MODE_PRIVATE)

    private fun getToken(): String? {
        return sharedPreferences.getString("token", null)
    }

    fun getAllTweets(callback: GenericCallback<List<Tweet>>) {
        val token = getToken() ?: return
        val call = tweetService.getAllTweets(token.let { "Bearer $it" })
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

    fun getUserTweets(userDetailId: String?, callback: GenericCallback<List<Tweet>>) {
        val token = getToken() ?: return
        val jwt = JWT(token)
        val userId = userDetailId ?: jwt.subject

        val call = tweetService.getUserTweets(token.let { "Bearer $it" }, userId!!)
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

    fun getFollowingUsersTweets(callback: GenericCallback<List<Tweet>>) {
        val token = getToken() ?: return
        val call = tweetService.getFollowingUsersTweets(token.let { "Bearer $it" })
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

    fun getLikesTweets(userDetailId: String?, callback: GenericCallback<List<Tweet>>) {
        val token = getToken() ?: return
        val jwt = JWT(token)
        val userId = userDetailId ?: jwt.subject

        val call = tweetService.getLikesTweets(token.let { "Bearer $it" }, userId!!)
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

    fun getRetweetsTweets(userDetailId: String?, callback: GenericCallback<List<Tweet>>) {
        val token = getToken() ?: return
        val jwt = JWT(token)
        val userId = userDetailId ?: jwt.subject

        val call = tweetService.getRetweetsTweets(token.let { "Bearer $it" }, userId!!)
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

    fun getTweetById(tweetId: String, callback: GenericCallback<Tweet>) {
        val token = getToken() ?: return
        val call = tweetService.getTweetById(token.let { "Bearer $it" }, tweetId)

        call.enqueue(object : Callback<TweetDto> {
            override fun onResponse(call: Call<TweetDto>, response: Response<TweetDto>) {
                val bodyResponse = response.body()

                if (bodyResponse != null) {
                    callback.onSuccess(tweetDtoToTweetModel(bodyResponse))
                }
            }

            override fun onFailure(call: Call<TweetDto>, t: Throwable) {
                callback.onError("Erreur réseau : ${t.message}")
            }
        })
    }

    fun sendTweet(content: String, callback: GenericCallback<TweetResponse>) {
        val token = getToken() ?: return

        val request = TweetRequest(content)
        val call = tweetService.sendTweet(token.let { "Bearer $it" }, request)

        call.enqueue(object : Callback<TweetResponse> {
            override fun onResponse(call: Call<TweetResponse>, response: Response<TweetResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { callback.onSuccess(it) }
                } else {
                    callback.onError("Erreur serveur : ${response.code()}")
                }
            }

            override fun onFailure(call: Call<TweetResponse>, t: Throwable) {
                callback.onError("Erreur réseau : ${t.message}")
            }
        })
    }

    fun likeTweet(tweetId: String, callback: GenericCallback<Unit>) {
        val token = getToken() ?: return
        val request = LikeRequest(tweetId)
        val call = tweetService.likeTweet(token.let { "Bearer $it" }, request)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    callback.onSuccess(Unit)
                } else {
                    callback.onError("Erreur ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                callback.onError("Erreur réseau : ${t.message}")
            }
        })
    }

    fun commentTweet(data: CommentRequest, callback: GenericCallback<Unit>) {
        val token = getToken() ?: return
        val call = tweetService.commentTweet(token.let { "Bearer $it" }, data)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    callback.onSuccess(Unit)
                } else {
                    callback.onError("Erreur ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                callback.onError("Erreur réseau : ${t.message}")
            }
        })
    }

    fun unlikeTweet(data: UnlikeRequest, callback: GenericCallback<Unit>) {
        val token = getToken() ?: return
        val call = tweetService.unlikeTweet(token.let { "Bearer $it" }, data)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    callback.onSuccess(Unit)
                } else {
                    callback.onError("Erreur ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                callback.onError("Erreur réseau : ${t.message}")
            }
        })
    }

    fun retweetTweet(data: RetweetRequest, callback: GenericCallback<Unit>) {
        val token = getToken() ?: return
        val call = tweetService.retweetTweet(token.let { "Bearer $it" }, data)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    callback.onSuccess(Unit)
                } else {
                    callback.onError("Erreur ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                callback.onError("Erreur réseau : ${t.message}")
            }
        })
    }
}