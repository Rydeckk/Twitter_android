package com.example.twitter_like.viewmodel.factories

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.twitter_like.repositories.AuthRepository
import com.example.twitter_like.viewmodel.AuthViewModel


class AuthViewModelFactory(
    private val repository: AuthRepository,
    private val fragment: Fragment
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(repository, fragment) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}