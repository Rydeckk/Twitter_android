package com.example.twitter_like.views

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.twitter_like.pages.interfaces.AuthHandler
import com.example.twitter_like.views.pager_fragments.AuthFragment
import com.example.twitter_like.views.pager_fragments.LoginFragment
import com.example.twitter_like.views.pager_fragments.RegisterFragment

class AuthAdapter(
    activity: AuthFragment,
    private val pagerHandler: AuthHandler,
    private val mainPager: ViewPager2
) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }
    override fun createFragment(position: Int): Fragment {
        val registerPage = RegisterFragment.newInstance(pagerHandler)
        val loginPage = LoginFragment.newInstance(pagerHandler, mainPager)
        return when (position) {
            0 -> registerPage
            1 -> loginPage
            else -> loginPage
        }
    }
}