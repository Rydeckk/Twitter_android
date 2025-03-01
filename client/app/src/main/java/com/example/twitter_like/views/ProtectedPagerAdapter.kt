package com.example.twitter_like.views

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.twitter_like.pages.interfaces.ProtectedPageHandler
import com.example.twitter_like.views.pager_fragments.messagePage.ConversationFragment
import com.example.twitter_like.views.pager_fragments.ProtectedPageFragment
import com.example.twitter_like.views.pager_fragments.HomeFragment
import com.example.twitter_like.views.pager_fragments.ProfileFragment
import com.example.twitter_like.views.pager_fragments.SectionMessageFragment
import com.example.twitter_like.views.pager_fragments.searchPage.SearchGlobalFragment

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
        val profileFragment = ProfileFragment.newInstance()
        val sectionMessageFragment = SectionMessageFragment.newInstance(protectedPager, mainPager)
        val searchFragment = SearchGlobalFragment.newInstance()
        return when (position) {
            0 -> homeFragment
            1 -> searchFragment
            2 -> //TODO NotificationFragment
                Fragment()

            3 -> sectionMessageFragment

            4 -> profileFragment
            else -> homeFragment
        }
    }
}