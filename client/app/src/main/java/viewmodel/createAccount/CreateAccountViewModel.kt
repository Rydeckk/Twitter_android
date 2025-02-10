package com.example.twitter_like.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.twitter_like.api.RegisterRequest
import com.example.twitter_like.api.RegisterResponse
import com.example.twitter_like.api.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.twitter_like.ui.token.TokenManager
import android.content.Context


class CreateAccountViewModel(private val context: Context) : ViewModel() {

    // LiveData pour suivre l'état de l'inscription
    private val _registerSuccess = MutableLiveData<Boolean>()
    val registerSuccess: LiveData<Boolean> get() = _registerSuccess

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private val tokenManager = TokenManager(context)


    fun registerUser(username: String, email: String, password: String) {
        val request = RegisterRequest(username, email, password)

        RetrofitInstance.api.register(request).enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if (response.isSuccessful) {
                    val token = response.body()!!.access_token
                    tokenManager.saveToken(token)
                    _registerSuccess.postValue(true)

                } else {
                    _errorMessage.postValue("Erreur : ${response.code()} - ${response.message()}")
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                _errorMessage.postValue("Échec de la connexion : ${t.message}")
            }
        })
    }
}
