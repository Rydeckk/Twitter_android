package com.example.twitter_like.pages.interfaces

import androidx.viewpager2.widget.ViewPager2


interface ProtectedPageHandler {
    fun displayHomePage()
    fun displaySearchPage()
    fun displayNotificationPage()
    fun displayMessagesPage()
}

class ProtectedPageHandlerImpl(private val viewPager: ViewPager2): ProtectedPageHandler {
    override fun displayHomePage() {
        viewPager.currentItem = 0
    }
    override fun displaySearchPage() {
        viewPager.currentItem = 1
    }

    override fun displayNotificationPage() {
        viewPager.currentItem = 2
    }

    override fun displayMessagesPage() {
        viewPager.currentItem = 3
    }
}