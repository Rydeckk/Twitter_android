package com.example.twitter_like.viewmodel

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val _selectedFragment = MutableLiveData<Fragment>()

    val selectedFragment: LiveData<Fragment> get() = _selectedFragment

    fun setFragment(fragment: Fragment) {
        _selectedFragment.value = fragment
    }
}