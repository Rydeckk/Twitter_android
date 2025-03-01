package com.example.twitter_like.views.pager_fragments.messagePage

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.twitter_like.R
import com.example.twitter_like.data.model.message.Message
import com.example.twitter_like.data.model.user.User
import com.example.twitter_like.network.callback.GenericCallback
import com.example.twitter_like.pages.interfaces.MessagePagerHandler
import com.example.twitter_like.repositories.ConversationRepository
import com.example.twitter_like.repositories.MessageRepository
import com.example.twitter_like.repositories.UserRepository
import com.example.twitter_like.viewmodel.ConversationViewModel
import com.example.twitter_like.viewmodel.MessageViewModel
import com.example.twitter_like.viewmodel.UserViewModel
import com.example.twitter_like.viewmodel.factories.ConversationViewModelFactory
import com.example.twitter_like.viewmodel.factories.MessageViewModelFactory
import com.example.twitter_like.viewmodel.factories.UserViewModelFactory
import com.example.twitter_like.views.recycler_views_adapters.home_adapters.MessageRvAdapter

class MessageFragment: Fragment(R.layout.message_fragment) {
    private lateinit var messageRv: RecyclerView

    private val conversationViewModel: ConversationViewModel by activityViewModels {
        ConversationViewModelFactory(ConversationRepository(requireContext()))
    }

    private val messageViewModel: MessageViewModel by activityViewModels {
        MessageViewModelFactory(MessageRepository(requireContext()), conversationViewModel)
    }

    private val userViewModel: UserViewModel by activityViewModels {
        UserViewModelFactory(UserRepository(requireContext()))
    }

    companion object {
        fun newInstance(): MessageFragment {
            return MessageFragment().also {
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val conversationSelected = conversationViewModel.selectedConversation.value
        val backButton = view.findViewById<ImageButton>(R.id.button_return)
        val inputMessage = view.findViewById<EditText>(R.id.message_input)
        val sendMessageButton = view.findViewById<ImageButton>(R.id.send_button)

        if(conversationSelected != null) {
            val usernameConversation = view.findViewById<TextView>(R.id.conversation_username)
            usernameConversation.text = conversationSelected.conversationsUsers
                .joinToString(",") { it.users.username }
        }

        messageViewModel.messages.observe(viewLifecycleOwner) { messages ->
            userViewModel.getCurrentUser(object: GenericCallback<User> {
                override fun onSuccess(data: User) {
                    setUpMessageRv(messages, data, view)
                    messageRv.scrollToPosition(messages.size - 1)
                }

                override fun onError(error: String) {
                    TODO("Not yet implemented")
                }
            })
        }

        backButton.setOnClickListener {
            conversationViewModel.clearSelectedConversation()
            (parentFragment as? MessagePagerHandler)?.displayConversation()
        }

        sendMessageButton.setOnClickListener {
            val messageContent = inputMessage.text.toString().trim()
            if (messageContent.isNotEmpty() && conversationSelected != null) {
                messageViewModel.sendMessage(conversationSelected.id, messageContent)
                inputMessage.text.clear()
            } else {
                Toast.makeText(requireContext(), "Le message est vide", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setUpMessageRv(messages: List<Message>, currentUser: User, fragmentView: View) {
        this.messageRv = fragmentView.findViewById(R.id.message_rv)
        this.messageRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter = MessageRvAdapter(messages, currentUser)
        this.messageRv.adapter = adapter
    }

}