package com.example.twitter_like.repositories

import android.content.Context
import android.content.SharedPreferences
import com.example.twitter_like.data.model.user.User
import com.example.twitter_like.data.request.user.UpdateUserRequest
import com.example.twitter_like.network.RetrofitClient
import com.example.twitter_like.network.callback.GenericCallback
import com.example.twitter_like.network.dto.users_dto.UserDto
import com.example.twitter_like.network.mapper.userDtoToUserModel
import com.example.twitter_like.network.services.UserService
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class UserRepository(private val context: Context) {
    private val userService = RetrofitClient.instance.create(UserService::class.java)
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MY_APP_SHARED_PREFS", Context.MODE_PRIVATE)

    private fun getToken(): String? {
        return sharedPreferences.getString("token", null)?.let { "Bearer $it" }
    }

    fun getUserById(userId: String, callback: GenericCallback<User>) {
        val token = getToken() ?: return
        val call = userService.getUserById(token, userId)
        call.enqueue(object : Callback<UserDto> {
            override fun onResponse(
                call: Call<UserDto>,
                response: Response<UserDto>
            ) {
                val bodyResponse = response.body()!!
                callback.onSuccess(userDtoToUserModel(bodyResponse))
            }

            override fun onFailure(call: Call<UserDto>, t: Throwable) {
                callback.onError("Erreur réseau : ${t.message}")
            }
        })
    }

    fun updateUserById(
        data: UpdateUserRequest,
        userId: String,
        callback: GenericCallback<Boolean>
    ) {
        val token = getToken() ?: return
        val call = userService.updateUserById(token, userId, data)

        call.enqueue(object : Callback<UpdateUserRequest> {
            override fun onResponse(
                call: Call<UpdateUserRequest>,
                response: Response<UpdateUserRequest>
            ) {
                callback.onSuccess(true)
            }

            override fun onFailure(call: Call<UpdateUserRequest>, t: Throwable) {
                callback.onError("Erreur réseau : ${t.message}")
            }

        })
    }

}