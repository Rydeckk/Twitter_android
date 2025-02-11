package com.example.twitter_like.pages.interfaces

import androidx.viewpager2.widget.ViewPager2

interface AuthHandler {
    fun displayRegisterPage()
    fun displayLoginPage()
}

class AuthHandlerImpl(private val viewPager: ViewPager2) : AuthHandler {
    override fun displayRegisterPage() {
        viewPager.currentItem = 0
    }

    override fun displayLoginPage() {
        viewPager.currentItem = 1
    }
}