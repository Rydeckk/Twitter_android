package com.example.twitter_like.views.recycler_views_adapters.home_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.twitter_like.R
import androidx.recyclerview.widget.RecyclerView
import com.example.twitter_like.data.model.follow.Follows
import com.example.twitter_like.views.view_holders.home_vh.FollowsRvViewHolder

class FollowsRvAdapter(private val follows: List<Follows>) :
    RecyclerView.Adapter<FollowsRvViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowsRvViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user, parent, false)
        return FollowsRvViewHolder(view)
    }

    override fun getItemCount(): Int {
        return follows.size
    }

    override fun onBindViewHolder(holder: FollowsRvViewHolder, position: Int) {
        val followData = this.follows[position]

        holder.fullname.text = followData.user.username
        holder.username.text = followData.user.username
        // holder.description.text = followData.user.biography
    }
}