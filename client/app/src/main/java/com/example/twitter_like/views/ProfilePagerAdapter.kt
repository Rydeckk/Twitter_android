package com.example.twitter_like.views

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.twitter_like.views.pager_fragments.ProfileFragment
import com.example.twitter_like.views.pager_fragments.profilePage.LikesPageFragment
import com.example.twitter_like.views.pager_fragments.profilePage.PostsPageFragment

class ProfilePagerAdapter(activity: ProfileFragment) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        val postsFragment = PostsPageFragment.newInstance()
        val likedFragment = LikesPageFragment.newInstance()
        return when (position) {
            0 -> postsFragment
            1 -> Fragment()
            2 -> Fragment()
            3 -> likedFragment
            else -> postsFragment
        }
    }
}
