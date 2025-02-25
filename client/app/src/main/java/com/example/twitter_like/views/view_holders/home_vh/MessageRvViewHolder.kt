package com.example.twitter_like.views.view_holders.home_vh

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.twitter_like.R

class MessageRvViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val message_owner = itemView.findViewById<TextView>(R.id.message_owner)
    val message_content = itemView.findViewById<TextView>(R.id.message_content)
    val date_message = itemView.findViewById<TextView>(R.id.message_time)
}