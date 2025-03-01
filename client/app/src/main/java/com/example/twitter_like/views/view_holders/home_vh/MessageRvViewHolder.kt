package com.example.twitter_like.views.view_holders.home_vh

import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.twitter_like.R

class MessageRvViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val messageOwner: TextView = itemView.findViewById(R.id.message_owner)
    val messageContent: TextView = itemView.findViewById(R.id.message_content)
    val dateMessage: TextView = itemView.findViewById(R.id.message_time)
    val messageContainer: FrameLayout = itemView.findViewById(R.id.message_container)
    val mainLayout: LinearLayout = itemView.findViewById(R.id.main_layout)
}