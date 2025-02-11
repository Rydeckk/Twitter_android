package com.example.twitter_like.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.twitter_like.data.model.auth.Register
import com.example.twitter_like.data.model.auth.Login
import com.example.twitter_like.data.request.auth.LoginRequest
import com.example.twitter_like.data.request.auth.RegisterRequest
import com.example.twitter_like.network.RetrofitClient
import com.example.twitter_like.network.callback.GenericCallback
import com.example.twitter_like.network.dto.auth_dto.RegisterDto
import com.example.twitter_like.network.dto.auth_dto.LoginDto
import com.example.twitter_like.network.mapper.loginDtoToLoginModel
import com.example.twitter_like.network.services.AuthService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AuthRepository {
    private val authService = RetrofitClient.instance.create(AuthService::class.java)

    private val _loginData = MutableLiveData<Login?>()
    val loginData: MutableLiveData<Login?> get() = _loginData

    fun registerUser(registerData: RegisterRequest, callback: GenericCallback<Register>) {
        val call = authService.register(registerData)
        call.enqueue(object : Callback<RegisterDto?> {
            override fun onResponse(call: Call<RegisterDto?>, response: Response<RegisterDto?>) {
                val status = response.body()?.status?.toInt()
                if (response.isSuccessful && status == 200) {
                    callback.onSuccess(Register(200))
                } else {
                    callback.onError("Erreur : ${status}")
                }
            }
            override fun onFailure(call: Call<RegisterDto?>, t: Throwable) {
                callback.onError("Erreur réseau : ${t.message}")
            }
        })
    }

    fun userLogin(data: LoginRequest, callback: GenericCallback<Login>) {
        val call = authService.login(data)
        call.enqueue(object : Callback<LoginDto?> {
            override fun onResponse(call: Call<LoginDto?>, response: Response<LoginDto?>) {
                val accessToken = response.body()?.accessToken
                if (accessToken?.isNotEmpty() == true) {
                    callback.onSuccess(Login(accessToken))
                } else {
                    callback.onError("Erreur : ${response.code()}")
                }
            }
            override fun onFailure(call: Call<LoginDto?>, t: Throwable) {
                callback.onError("Erreur réseau : ${t.message}")
            }
        })
    }
}