package com.example.twitter_like.views.pager_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.twitter_like.R
import com.example.twitter_like.pages.interfaces.ProfilePagerHandler
import com.example.twitter_like.views.ProfilePagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class ProfileFragment : Fragment(), ProfilePagerHandler {
    private lateinit var profileViewPager: ViewPager2

    companion object {
        fun newInstance(): ProfileFragment {
            return ProfileFragment().also {

            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.profile_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabLayout = view.findViewById<TabLayout>(R.id.tab_profile_layout)
        val viewPager = view.findViewById<ViewPager2>(R.id.profile_view_page)
        val adapter = ProfilePagerAdapter(this)
        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Posts"
                1 -> "Replies"
                2 -> "Media"
                3 -> "Likes"
                else -> null
            }
        }.attach()
    }

    override fun displayPosts() {
        profileViewPager.currentItem = 0;
    }

    override fun displayReplies() {
        profileViewPager.currentItem = 1;
    }

    override fun displayMedias() {
        profileViewPager.currentItem = 2;
    }

    override fun displayLikes() {
        profileViewPager.currentItem = 3;
    }

}