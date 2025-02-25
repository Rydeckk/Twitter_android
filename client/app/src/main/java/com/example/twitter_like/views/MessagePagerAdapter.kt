package com.example.twitter_like.views

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.twitter_like.pages.interfaces.MessagePagerHandler
import com.example.twitter_like.pages.interfaces.ProtectedPageHandler
import com.example.twitter_like.views.pager_fragments.messagePage.ConversationFragment
import com.example.twitter_like.views.pager_fragments.messagePage.MessageFragment
import com.example.twitter_like.views.pager_fragments.SectionMessageFragment

class MessagePagerAdapter(
    activity: SectionMessageFragment,
    private val messagePagerHandler: MessagePagerHandler,
    private val protectedPager: ProtectedPageHandler
): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        val conversationFragment = ConversationFragment.newInstance()
        val messageFragment = MessageFragment.newInstance()
        return when (position) {
            0 -> conversationFragment
            1 -> messageFragment
            else -> conversationFragment
        }
    }
}