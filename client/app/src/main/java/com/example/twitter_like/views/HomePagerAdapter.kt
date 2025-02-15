package com.example.twitter_like.views

import android.util.Log
import androidx.fragment.app.Fragment
import com.example.twitter_like.views.pager_fragments.HomeFragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.twitter_like.pages.interfaces.HomePagerHandler
import com.example.twitter_like.pages.interfaces.ProtectedPageHandler
import com.example.twitter_like.views.pager_fragments.homePage.AllTweetFragment
import com.example.twitter_like.views.pager_fragments.homePage.FollowingUsersTweets

class HomePagerAdapter(
    activity: HomeFragment,
    private val homePager: HomePagerHandler,
    private val protectedPager: ProtectedPageHandler,
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        val allTweetFragment = AllTweetFragment.newInstance()
        val followingUsersTweets = FollowingUsersTweets.newInstance()
        return when (position) {
            0 -> allTweetFragment
            1 -> followingUsersTweets
            else -> allTweetFragment
        }
    }

}