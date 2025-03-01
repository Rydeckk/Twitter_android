package com.example.twitter_like.views.view_holders.home_vh

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.twitter_like.R

class SearchGroupRvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val username: TextView = itemView.findViewById(R.id.username_text)
    val checkUser: ImageView = itemView.findViewById(R.id.user_check)
}