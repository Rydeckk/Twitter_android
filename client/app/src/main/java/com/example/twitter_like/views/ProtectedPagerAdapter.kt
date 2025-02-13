package com.example.twitter_like.views

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.twitter_like.pages.interfaces.ProtectedPageHandler
import com.example.twitter_like.views.pager_fragments.ProtectedPageFragment
import com.example.twitter_like.views.pager_fragments.HomeFragment

class ProtectedPagerAdapter(
    activity: ProtectedPageFragment,
    private val pagerHandler: ProtectedPageHandler,
    private val viewPager: ViewPager2
) :
    FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> //TODO SearchFragment
                Fragment()
            2 -> //TODO NotificationFragment
                Fragment()
            3 -> //TODO MessagesFragment
                Fragment()
            else -> HomeFragment()
        }
    }
}