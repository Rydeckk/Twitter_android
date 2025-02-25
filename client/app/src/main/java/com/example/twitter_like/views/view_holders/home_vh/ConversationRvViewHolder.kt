package com.example.twitter_like.views.view_holders.home_vh

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.twitter_like.R

class ConversationRvViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val title = itemView.findViewById<TextView>(R.id.tvTitle)
    val lastMessage = itemView.findViewById<TextView>(R.id.tvLastMessage)
    val timestamp = itemView.findViewById<TextView>(R.id.tvTimestamp)
}