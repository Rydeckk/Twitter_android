package com.example.twitter_like.viewmodel

import androidx.lifecycle.ViewModel
import com.example.twitter_like.data.model.user.User
import com.example.twitter_like.data.request.user.UpdateUserRequest
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.twitter_like.network.callback.GenericCallback
import com.example.twitter_like.repositories.UserRepository

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    private val _filteredUsers = MutableLiveData<List<User>>()
    val filteredUsers: LiveData<List<User>> = _filteredUsers

    private val _selectedUsers = MutableLiveData<List<User>>()
    val selectedUsers: LiveData<List<User>> = _selectedUsers

    fun getUserById(userId: String, callback: GenericCallback<User>) {
        this.userRepository.getUserById(userId, object : GenericCallback<User> {
            override fun onSuccess(data: User) {
                callback.onSuccess(data)
            }

            override fun onError(error: String) {
                callback.onError(error)
            }
        })
    }

    fun updateUserById(
        data: UpdateUserRequest,
        userId: String,
        callback: GenericCallback<Boolean>
    ) {
        this.userRepository.updateUserById(data, userId, object : GenericCallback<Boolean> {
            override fun onSuccess(data: Boolean) {
                callback.onSuccess(data)
            }

            override fun onError(error: String) {
                callback.onError(error)
            }
        })
    }

    fun fetchUsers() {
        userRepository.getAllUsers(object : GenericCallback<List<User>> {
            override fun onSuccess(data: List<User>) {
                _users.postValue(data)
                _filteredUsers.postValue(data)
            }

            override fun onError(error: String) {
                Log.d("Error", error)
            }
        })
    }

    fun filterUsers(query: String) {
        val filteredList = if (query.isEmpty()) {
            _users.value ?: emptyList()
        } else {
            _users.value?.filter { it.username.contains(query, ignoreCase = true) } ?: emptyList()
        }
        _filteredUsers.postValue(filteredList)
    }

    fun getCurrentUser(callback: GenericCallback<User>) {
        userRepository.getCurrentUser(object : GenericCallback<User> {
            override fun onSuccess(data: User) {
                callback.onSuccess(data)
            }

            override fun onError(error: String) {
                callback.onError(error)
            }
        })
    }

    fun addDeleteSelectedUser(user: User) {
        val currentList = _selectedUsers.value ?: emptyList()
        if (currentList.contains(user)) {
            _selectedUsers.postValue(currentList.filter { !it.id.contains(user.id) })
        } else {
            _selectedUsers.postValue(currentList + user)
        }
    }

    fun clearSelectedUser() {
        _selectedUsers.postValue(emptyList())
    }
}