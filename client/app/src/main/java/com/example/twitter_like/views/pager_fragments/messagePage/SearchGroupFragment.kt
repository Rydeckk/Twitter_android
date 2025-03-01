package com.example.twitter_like.views.pager_fragments.messagePage

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.twitter_like.R
import com.example.twitter_like.data.model.user.User
import com.example.twitter_like.data.request.conversation.ConversationCreateRequest
import com.example.twitter_like.pages.interfaces.MessagePagerHandler
import com.example.twitter_like.repositories.ConversationRepository
import com.example.twitter_like.repositories.UserRepository
import com.example.twitter_like.viewmodel.ConversationViewModel
import com.example.twitter_like.viewmodel.UserViewModel
import com.example.twitter_like.viewmodel.factories.ConversationViewModelFactory
import com.example.twitter_like.viewmodel.factories.UserViewModelFactory
import com.example.twitter_like.views.recycler_views_adapters.home_adapters.SearchGroupRvAdapter

class SearchGroupFragment: Fragment(R.layout.search_group_fragment) {
    private lateinit var searchRv: RecyclerView
    private val userViewModel: UserViewModel by activityViewModels {
        UserViewModelFactory(UserRepository(requireContext()))
    }

    private val conversationViewModel: ConversationViewModel by activityViewModels {
        ConversationViewModelFactory(ConversationRepository(requireContext()))
    }

    companion object {
        fun newInstance(): SearchGroupFragment {
            return SearchGroupFragment().also {
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchInput = view.findViewById<EditText>(R.id.search_user_group)
        val returnButton = view.findViewById<ImageButton>(R.id.button_return)
        val addConversation: ImageButton = view.findViewById(R.id.add_conversation)

        userViewModel.filteredUsers.observe(viewLifecycleOwner) { users ->
            val selectedUsers = userViewModel.selectedUsers.value ?: emptyList()
            setUpSearchGroupRv(users, selectedUsers, view)
        }

        userViewModel.selectedUsers.observe(viewLifecycleOwner) { users ->
            val filteredUsers = userViewModel.filteredUsers.value ?: emptyList()
            setUpSearchGroupRv(filteredUsers,users, view)
        }

        userViewModel.fetchUsers()

        returnButton.setOnClickListener {
            (parentFragment as? MessagePagerHandler)?.displayConversation()
        }

        addConversation.setOnClickListener {
            val selectedUsers = userViewModel.selectedUsers.value ?: emptyList()
            val userIds = selectedUsers.map { it.id }
            conversationViewModel.createConversation(ConversationCreateRequest(userIds.toTypedArray()))
            userViewModel.clearSelectedUser()
        }

        searchInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                userViewModel.filterUsers(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun setUpSearchGroupRv(users: List<User>,selectedUser: List<User>, fragmentView: View) {
        this.searchRv = fragmentView.findViewById(R.id.rv_search_group)
        this.searchRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter = SearchGroupRvAdapter(users, selectedUser)  { user ->
            userViewModel.addDeleteSelectedUser(user)
        }
        this.searchRv.adapter = adapter
    }
}