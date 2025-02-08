package com.example.twitter_like.pages

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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

        findViewById<ImageButton>(R.id.bt_home).setOnClickListener {
            displayHomePage()
        }

        findViewById<ImageButton>(R.id.bt_search).setOnClickListener {
            displaySearchPage()
        }

        findViewById<ImageButton>(R.id.bt_notification).setOnClickListener {
            displayNotificationPage()
        }

        findViewById<ImageButton>(R.id.bt_message).setOnClickListener {
            displayMessagesPage()
        }

        this.dymagramPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateButtonColors(position)
            }
        })
    }

    private fun setUpMainPager() {
        this.dymagramPager = findViewById(R.id.main_pager)

        val mainPagerAdapter = ViewPagerAdapter(this, this)
        this.dymagramPager.adapter = mainPagerAdapter

        displayHomePage()
    }

    private fun updateButtonColors(activePage: Int) {
        val buttons = listOf(
            findViewById<ImageButton>(R.id.bt_home),
            findViewById<ImageButton>(R.id.bt_search),
            findViewById<ImageButton>(R.id.bt_notification),
            findViewById<ImageButton>(R.id.bt_message)
        )

        val activeColor = ContextCompat.getColor(this, R.color.active_color)
        val inactiveColor = ContextCompat.getColor(this, R.color.inactive_color)

        buttons.forEachIndexed { index, button ->
            button.setColorFilter(if (index == activePage) activeColor else inactiveColor)
        }
    }

    override fun displayHomePage() {
        this.dymagramPager.currentItem = 0
    }

    override fun displaySearchPage() {
        this.dymagramPager.currentItem = 1
    }

    override fun displayNotificationPage() {
        this.dymagramPager.currentItem = 2
    }

    override fun displayMessagesPage() {
        this.dymagramPager.currentItem = 3
    }
}