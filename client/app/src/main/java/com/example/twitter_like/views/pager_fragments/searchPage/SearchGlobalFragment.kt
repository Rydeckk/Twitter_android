package com.example.twitter_like.views.pager_fragments.searchPage

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.twitter_like.R
import com.example.twitter_like.data.model.user.User
import com.example.twitter_like.repositories.UserRepository
import com.example.twitter_like.viewmodel.UserViewModel
import com.example.twitter_like.viewmodel.factories.UserViewModelFactory
import com.example.twitter_like.views.recycler_views_adapters.home_adapters.SearchUserRvAdapter

class SearchGlobalFragment: Fragment(R.layout.search_global_fragment) {
    private lateinit var searchRv: RecyclerView
    private val userViewModel: UserViewModel by activityViewModels {
        UserViewModelFactory(UserRepository(requireContext()))
    }

    companion object {
        fun newInstance(): SearchGlobalFragment {
            return SearchGlobalFragment().also {
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchInput: EditText = view.findViewById(R.id.search_user)

        userViewModel.filteredUsers.observe(viewLifecycleOwner) { users ->
            setUpSearchUserRv(users, view)
        }

        userViewModel.fetchUsers()

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
        val adapter = SearchUserRvAdapter(users) { user ->
            //TODO
        }
        this.searchRv.adapter = adapter
    }
}