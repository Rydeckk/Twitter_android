package com.example.twitter_like.views

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.twitter_like.views.pager_fragments.AuthFragment
import com.example.twitter_like.views.pager_fragments.ProtectedPageFragment

class ViewPagerAdapter(
    activity: com.example.twitter_like.pages.MainActivity,
    private val viewPager: ViewPager2
) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }
    override fun createFragment(position: Int): Fragment {
        val authFragment = AuthFragment.newInstance(viewPager)
        val protectedPageFragment = ProtectedPageFragment.newInstance(viewPager)

        return when (position) {
            0 -> protectedPageFragment
            1 -> authFragment
            else -> authFragment
        }
    }
}