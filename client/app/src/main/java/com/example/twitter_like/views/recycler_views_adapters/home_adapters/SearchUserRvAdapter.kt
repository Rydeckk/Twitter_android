package com.example.twitter_like.views.recycler_views_adapters.home_adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.twitter_like.R
import com.example.twitter_like.data.model.user.User
import com.example.twitter_like.views.view_holders.home_vh.SearchUserRvViewHolder

class SearchUserRvAdapter(private var users: List<User>, private val onUserClick: (User) -> Unit): RecyclerView.Adapter<SearchUserRvViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchUserRvViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)

        return SearchUserRvViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: SearchUserRvViewHolder, position: Int) {

        val userData = users[position]
        holder.username.text = userData.username

        holder.itemView.setOnClickListener {
            onUserClick(userData)
        }
    }

}