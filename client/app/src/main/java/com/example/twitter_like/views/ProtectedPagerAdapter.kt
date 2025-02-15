package com.example.twitter_like.views

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.twitter_like.pages.interfaces.ProtectedPageHandler
import com.example.twitter_like.views.pager_fragments.ProtectedPageFragment
import com.example.twitter_like.views.pager_fragments.HomeFragment

class ProtectedPagerAdapter(
    activity: ProtectedPageFragment,
    private val protectedPager: ProtectedPageHandler,
    private val mainPager: ViewPager2
) :
    FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        val homeFragment = HomeFragment.newInstance(protectedPager, mainPager)
        return when (position) {
            0 -> homeFragment
            1 -> //TODO SearchFragment
                Fragment()
            2 -> //TODO NotificationFragment
                Fragment()
            3 -> //TODO MessagesFragment
                Fragment()
            4 -> // TODO UserProfilFragment
                Fragment()
            else -> homeFragment
        }
    }
}