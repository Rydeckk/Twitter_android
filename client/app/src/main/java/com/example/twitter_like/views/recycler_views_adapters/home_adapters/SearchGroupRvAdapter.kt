package com.example.twitter_like.views.recycler_views_adapters.home_adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.twitter_like.R
import com.example.twitter_like.data.model.user.User
import com.example.twitter_like.views.view_holders.home_vh.SearchGroupRvViewHolder

class SearchGroupRvAdapter(private var users: List<User>,private var selectedUsers: List<User>, private val onUserClick: (User) -> Unit): RecyclerView.Adapter<SearchGroupRvViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchGroupRvViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_group_item, parent, false)

        return SearchGroupRvViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: SearchGroupRvViewHolder, position: Int) {

        val userData = users[position]
        holder.username.text = userData.username
        if (selectedUsers.contains(userData)) {
            holder.itemView.setBackgroundResource(R.drawable.background_user_check)
            holder.checkUser.visibility = View.VISIBLE
        }

        holder.itemView.setOnClickListener {
            onUserClick(userData)
        }
    }

}