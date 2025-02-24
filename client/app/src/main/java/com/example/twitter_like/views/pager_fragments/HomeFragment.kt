package com.example.twitter_like.views.pager_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.twitter_like.R
import com.example.twitter_like.pages.interfaces.HomePagerHandler
import com.example.twitter_like.pages.interfaces.ProtectedPageHandler
import com.example.twitter_like.ui.NewTweetModal
import com.example.twitter_like.views.HomePagerAdapter
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

    override fun displayAllTweet() {
        homeDynagramPager.currentItem = 0;
    }

    override fun displayFollowingUsersTweets() {
        homeDynagramPager.currentItem = 1;
    }


}