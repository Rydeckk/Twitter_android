package com.example.twitter_like.views

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.twitter_like.views.pager_fragments.AuthFragment
import com.example.twitter_like.pages.interfaces.AuthHandlerImpl
import com.example.twitter_like.pages.interfaces.PagerHandler
import com.example.twitter_like.views.pager_fragments.TweetFragment

class ViewPagerAdapter(
    activity: com.example.twitter_like.pages.MainActivity,
    private val pagerHandler: PagerHandler,
    private val viewPager: ViewPager2
) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        val authFragment = AuthFragment.newInstance(AuthHandlerImpl(viewPager))
        val homePage = TweetFragment.newInstance(pagerHandler)

        return when (position) {
            0 -> homePage
            1 -> //TODO SearchFragment
                Fragment()

            2 -> //TODO NotificationFragment
                Fragment()

            3 -> //TODO MessagesFragment
                Fragment()
            4 -> authFragment
            else -> authFragment
        }
    }
}