package com.example.twitter_like.pages

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.twitter_like.R
import com.example.twitter_like.pages.interfaces.PagerHandler
import com.example.twitter_like.views.ViewPagerAdapter

class MainActivity : AppCompatActivity(), PagerHandler {
    private lateinit var dymagramPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        setUpMainPager()
    }

    private fun setUpMainPager() {
        this.dymagramPager = findViewById(R.id.main_pager)
        val mainPagerAdapter = ViewPagerAdapter(this, this.dymagramPager)
        this.dymagramPager.adapter = mainPagerAdapter

        val sharedPreferences = this.getSharedPreferences("MY_APP_SHARED_PREFS", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("token", null)
        // COMMENT to remove token
        // sharedPreferences.edit().remove("token").apply()
        if (token.isNullOrEmpty()) {
            displayAuthFragment()
        } else {
            displayProtectedFragment()
        }
    }

    override fun displayProtectedFragment() {
        this.dymagramPager.currentItem = 0
    }

    override fun displayAuthFragment() {
        this.dymagramPager.currentItem = 1
    }
}