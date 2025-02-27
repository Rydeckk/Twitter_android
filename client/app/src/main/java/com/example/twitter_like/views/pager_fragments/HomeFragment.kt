package com.example.twitter_like.views.pager_fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.twitter_like.R
import com.example.twitter_like.pages.interfaces.HomePagerHandler
import com.example.twitter_like.pages.interfaces.ProtectedPageHandler
import com.example.twitter_like.views.pager_fragments.modal.NewTweetModal
import com.example.twitter_like.views.HomePagerAdapter
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment(), HomePagerHandler {
    private lateinit var _mainPager: ViewPager2
    private lateinit var _protectedPager: ProtectedPageHandler
    private lateinit var homeDynagramPager: ViewPager2

    companion object {
        fun newInstance(protectedPager: ProtectedPageHandler, mainPager: ViewPager2): HomeFragment {
            return HomeFragment().also {
                it._mainPager = mainPager
                it._protectedPager = protectedPager
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.home_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        val viewPager = view.findViewById<ViewPager2>(R.id.home_view_pager)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val position = tab?.position ?: return
                updateNavigation(position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        val adapter = HomePagerAdapter(this, this, this._protectedPager)
        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Pour vous"
                1 -> "Abonnements"
                else -> null
            }
        }.attach()

        val tweetButton = view.findViewById<ImageView>(R.id.tweet_button)
        tweetButton.setOnClickListener {
            val modal = NewTweetModal()
            modal.show(parentFragmentManager, "NewTweetModal")
        }

    }

    private fun updateNavigation(position: Number) {
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.home_fragment_navigation) as? NavHostFragment

        val navController = navHostFragment?.navController
            ?: view?.findViewById<FragmentContainerView>(R.id.home_fragment_navigation)
                ?.getFragment<NavHostFragment>()?.navController

        if (navController == null) {
            return
        }

        if (position == 0) {
            navController.setGraph(R.navigation.home_navigation)
        } else {
            navController.setGraph(R.navigation.following_users_tweets_navigation)
        }
    }

    override fun displayAllTweet() {
        homeDynagramPager.currentItem = 0;
    }

    override fun displayFollowingUsersTweets() {
        homeDynagramPager.currentItem = 1;
    }
}