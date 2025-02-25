package com.example.twitter_like.views.pager_fragments.messagePage

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.twitter_like.R
import com.example.twitter_like.data.model.message.Message
import com.example.twitter_like.network.callback.GenericCallback
import com.example.twitter_like.repositories.ConversationRepository
import com.example.twitter_like.repositories.MessageRepository
import com.example.twitter_like.viewmodel.ConversationViewModel
import com.example.twitter_like.viewmodel.MessageViewModel
import com.example.twitter_like.viewmodel.factories.ConversationViewModelFactory
import com.example.twitter_like.viewmodel.factories.MessageViewModelFactory
import com.example.twitter_like.views.recycler_views_adapters.home_adapters.MessageRvAdapter

class MessageFragment: Fragment(R.layout.message_fragment) {
    private lateinit var messageRv: RecyclerView

    private val conversationViewModel: ConversationViewModel by activityViewModels {
        ConversationViewModelFactory(ConversationRepository(requireContext()))
    }

    private val messageViewModel: MessageViewModel by activityViewModels {
        MessageViewModelFactory(MessageRepository(requireContext()), conversationViewModel)
    }

    companion object {
        fun newInstance(): MessageFragment {
            return MessageFragment().also {
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        messageViewModel.messages.observe(viewLifecycleOwner) { messages ->
            Log.d("MessageFragment", "Messages reçus : ${messages.size}")
            setUpMessageRv(messages, view)
        }
    }

    private fun setUpMessageRv(messages: List<Message>, fragmentView: View) {
        this.messageRv = fragmentView.findViewById(R.id.message_rv)
        this.messageRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter = MessageRvAdapter(messages)
        this.messageRv.adapter = adapter
    }

}