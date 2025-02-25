package com.example.twitter_like.views.pager_fragments.messagePage

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.twitter_like.R
import com.example.twitter_like.data.model.conversation.Conversation
import com.example.twitter_like.network.callback.GenericCallback
import com.example.twitter_like.repositories.ConversationRepository
import com.example.twitter_like.viewmodel.ConversationViewModel
import com.example.twitter_like.viewmodel.factories.ConversationViewModelFactory
import com.example.twitter_like.views.recycler_views_adapters.home_adapters.ConversationRvAdapter

class ConversationFragment: Fragment(R.layout.conversation_fragment) {
    private lateinit var conversationRv: RecyclerView

    companion object {
        fun newInstance(): ConversationFragment {
            return ConversationFragment().also {
            }
        }
    }

    private val conversationViewModel: ConversationViewModel by activityViewModels {
        ConversationViewModelFactory(ConversationRepository(requireContext()))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchData(view)
    }

    private fun setUpConversationRv(conversations: List<Conversation>, fragmentView: View) {
        this.conversationRv = fragmentView.findViewById(R.id.conversation_rv)
        this.conversationRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter = ConversationRvAdapter(conversations) { conversation ->
            conversationViewModel.selectConversation(conversation)
        }

        this.conversationRv.adapter = adapter
    }


    private fun fetchData(fragmentView: View) {
        conversationViewModel.getConversation(object : GenericCallback<List<Conversation>> {
            override fun onSuccess(data: List<Conversation>) {
                setUpConversationRv(data, fragmentView)
            }
            override fun onError(error: String) {
                TODO("Not yet implemented")
            }
        })
    }
}