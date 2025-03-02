package com.example.twitter_like.views

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.twitter_like.views.pager_fragments.profilePage.LikesPageFragment
import com.example.twitter_like.views.pager_fragments.profilePage.PostsPageFragment
import com.example.twitter_like.views.pager_fragments.profilePage.RetweetsPageFragment

class ProfilePagerAdapter(activity: Fragment) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        val postsFragment = PostsPageFragment.newInstance(null)
        val likedFragment = LikesPageFragment.newInstance(null)
        val retweetsFragment = RetweetsPageFragment.newInstance(null)
        return when (position) {
            0 -> postsFragment
            1 -> retweetsFragment
            2 -> Fragment()
            3 -> likedFragment
            else -> postsFragment
        }
    }
}
