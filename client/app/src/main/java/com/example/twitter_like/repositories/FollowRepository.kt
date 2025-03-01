package com.example.twitter_like.repositories

import android.content.Context
import android.content.SharedPreferences
import com.example.twitter_like.data.model.follow.Follows
import com.example.twitter_like.data.request.follow.FollowRequest
import com.example.twitter_like.data.request.follow.UnfollowRequest
import com.example.twitter_like.network.RetrofitClient
import com.example.twitter_like.network.callback.GenericCallback
import com.example.twitter_like.network.dto.follow_dto.FollowedByDto
import com.example.twitter_like.network.dto.follow_dto.FollowingDto
import com.example.twitter_like.network.mapper.followedByDtoToFollowMapper
import com.example.twitter_like.network.mapper.followingDtoToFollowMapper
import com.example.twitter_like.network.services.FollowService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowRepository(private val context: Context) {
    private val followService = RetrofitClient.instance.create(FollowService::class.java)
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MY_APP_SHARED_PREFS", Context.MODE_PRIVATE)

    private fun getToken(): String? {
        return sharedPreferences.getString("token", null)?.let { "Bearer $it" }
    }

    fun getUserFollowers(userId: String, callback: GenericCallback<List<Follows>>) {
        val token = getToken() ?: return
        val call = followService.getUserFollowers(token, userId)

        call.enqueue(object : Callback<List<FollowedByDto>> {
            override fun onResponse(
                call: Call<List<FollowedByDto>>,
                response: Response<List<FollowedByDto>>
            ) {
                if (response.isSuccessful) {
                    val bodyResponse = response.body()
                    if (bodyResponse != null) {
                        val follows = bodyResponse.map { followedByDtoToFollowMapper(it) }
                        callback.onSuccess(follows)
                    } else {
                        callback.onSuccess(emptyList())
                    }
                } else {
                    callback.onError("Erreur serveur : ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<FollowedByDto>>, t: Throwable) {
                callback.onError("Erreur réseau : ${t.message}")
            }
        })
    }

    fun getUserFollowings(userId: String, callback: GenericCallback<List<Follows>>) {
        val token = getToken() ?: return
        val call = followService.getUserFollowings(token, userId)

        call.enqueue(object : Callback<List<FollowingDto>> {
            override fun onResponse(
                call: Call<List<FollowingDto>>,
                response: Response<List<FollowingDto>>
            ) {
                if (response.isSuccessful) {
                    val bodyResponse = response.body()
                    if (bodyResponse != null) {
                        val follows = bodyResponse.map { followingDtoToFollowMapper(it) }
                        callback.onSuccess(follows)
                    } else {
                        callback.onSuccess(emptyList())
                    }
                } else {
                    callback.onError("Erreur serveur : ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<FollowingDto>>, t: Throwable) {
                callback.onError("Erreur réseau : ${t.message}")
            }
        })

    }


    fun followUser(data: FollowRequest, callback: GenericCallback<Unit>) {
        val token = getToken() ?: return
        val call = followService.followUser(token, data)
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

    fun unfollowUser(data: UnfollowRequest, callback: GenericCallback<Unit>) {
        val token = getToken() ?: return
        val call = followService.unfollowUser(token, data)
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