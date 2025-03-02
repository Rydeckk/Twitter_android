package com.example.twitter_like.views.pager_fragments.messagePage

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.twitter_like.R
import com.example.twitter_like.data.model.user.User
import com.example.twitter_like.viewmodel.UserViewModel
import com.example.twitter_like.views.recycler_views_adapters.home_adapters.SearchUserRvAdapter
import android.text.TextWatcher
import android.text.Editable
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import com.example.twitter_like.data.model.conversation.Conversation
import com.example.twitter_like.data.request.conversation.ConversationCreateRequest
import com.example.twitter_like.network.callback.GenericCallback
import com.example.twitter_like.pages.interfaces.MessagePagerHandler
import com.example.twitter_like.repositories.ConversationRepository
import com.example.twitter_like.repositories.UserRepository
import com.example.twitter_like.viewmodel.ConversationViewModel
import com.example.twitter_like.viewmodel.factories.ConversationViewModelFactory
import com.example.twitter_like.viewmodel.factories.UserViewModelFactory

class SearchFragment: Fragment(R.layout.search_fragment) {
    private lateinit var searchRv: RecyclerView
    private val userViewModel: UserViewModel by activityViewModels {
        UserViewModelFactory(UserRepository(requireContext()))
    }

    private val conversationViewModel: ConversationViewModel by activityViewModels {
        ConversationViewModelFactory(ConversationRepository(requireContext()))
    }

    companion object {
        fun newInstance(): SearchFragment {
            return SearchFragment().also {
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchInput: EditText = view.findViewById(R.id.search_user)
        val returnButton: ImageButton = view.findViewById(R.id.button_return)
        val layoutCreateGroup: LinearLayout = view.findViewById(R.id.layout_create_group)

        userViewModel.filteredUsers.observe(viewLifecycleOwner) { users ->
            setUpSearchUserRv(users, view)
        }

        userViewModel.fetchUsers()

        returnButton.setOnClickListener {
            (parentFragment as? MessagePagerHandler)?.displayConversation()
        }

        layoutCreateGroup.setOnClickListener {
            (parentFragment as? MessagePagerHandler)?.displaySearchGroup()
        }

        searchInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                userViewModel.filterUsers(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun setUpSearchUserRv(users: List<User>, fragmentView: View) {
        this.searchRv = fragmentView.findViewById(R.id.rv_search_user)
        this.searchRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter = SearchUserRvAdapter(users)  { user ->
            conversationViewModel.createConversation(ConversationCreateRequest(arrayOf(user.id)), object: GenericCallback<Conversation> {
                override fun onSuccess(data: Conversation) {}

                override fun onError(error: String) {
                    Toast.makeText(requireContext(), "Conversation avec ces personnes déjà existante", Toast.LENGTH_SHORT).show()
                }

            })
        }
        this.searchRv.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        userViewModel.fetchUsers()
    }
}