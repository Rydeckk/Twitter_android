package com.example.twitter_like.views.pager_fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.twitter_like.R
import com.example.twitter_like.pages.interfaces.MessagePagerHandler
import com.example.twitter_like.pages.interfaces.ProtectedPageHandler
import com.example.twitter_like.repositories.ConversationRepository
import com.example.twitter_like.viewmodel.ConversationViewModel
import com.example.twitter_like.viewmodel.factories.ConversationViewModelFactory
import com.example.twitter_like.views.MessagePagerAdapter

class SectionMessageFragment: Fragment(), MessagePagerHandler {
    private lateinit var _mainPager: ViewPager2
    private lateinit var _protectedPager: ProtectedPageHandler
    private lateinit var section_message_pager: ViewPager2
    private val conversationViewModel: ConversationViewModel by activityViewModels {
        ConversationViewModelFactory(ConversationRepository(requireContext()))
    }

    companion object {
        fun newInstance(protectedPager: ProtectedPageHandler, mainPager: ViewPager2): SectionMessageFragment {
            return SectionMessageFragment().also {
                it._mainPager = mainPager
                it._protectedPager = protectedPager
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.section_message_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = view.findViewById<ViewPager2>(R.id.section_message_view_pager)
        this.section_message_pager = viewPager
        val adapter = MessagePagerAdapter(this)
        viewPager.adapter = adapter

        displayConversation()

        conversationViewModel.selectedConversation.observe(viewLifecycleOwner) { conversation ->
            if (conversation != null) {
                displayMessage()
            }
        }
    }

    override fun displayConversation() {
        this.section_message_pager.setCurrentItem(0,false)
    }

    override fun displayMessage() {
        this.section_message_pager.setCurrentItem(1,false)
    }

    override fun displaySearchUser() {
        this.section_message_pager.setCurrentItem(2,false)
    }

    override fun displaySearchGroup() {
        this.section_message_pager.setCurrentItem(3, false)
    }

}