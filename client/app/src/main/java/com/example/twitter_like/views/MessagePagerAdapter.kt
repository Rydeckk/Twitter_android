package com.example.twitter_like.views

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.twitter_like.views.pager_fragments.messagePage.ConversationFragment
import com.example.twitter_like.views.pager_fragments.messagePage.MessageFragment
import com.example.twitter_like.views.pager_fragments.SectionMessageFragment
import com.example.twitter_like.views.pager_fragments.messagePage.SearchFragment
import com.example.twitter_like.views.pager_fragments.messagePage.SearchGroupFragment

class MessagePagerAdapter(
    activity: SectionMessageFragment
): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        val conversationFragment = ConversationFragment.newInstance()
        val messageFragment = MessageFragment.newInstance()
        val searchFragment = SearchFragment.newInstance()
        val searchGroupFragment = SearchGroupFragment.newInstance()
        return when (position) {
            0 -> conversationFragment
            1 -> messageFragment
            2 -> searchFragment
            3 -> searchGroupFragment
            else -> conversationFragment
        }
    }
}