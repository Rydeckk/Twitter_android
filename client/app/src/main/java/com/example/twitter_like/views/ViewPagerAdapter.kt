package com.example.twitter_like.views

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.twitter_like.MainActivity
import com.example.twitter_like.pages.interfaces.PagerHandler

class ViewPagerAdapter(activity: MainActivity, private val pagerHandler: PagerHandler): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> //TODO HomeFragment
                Fragment()
            1 -> //TODO SearchFragment
                Fragment()
            2 -> //TODO NotificationFragment
                Fragment()
            3 -> //TODO MessagesFragment
                Fragment()
            else -> //TODO HomeFragment
                Fragment()
        }
    }
}