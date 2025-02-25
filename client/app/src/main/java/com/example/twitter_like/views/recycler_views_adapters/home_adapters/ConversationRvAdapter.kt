package com.example.twitter_like.views.recycler_views_adapters.home_adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.twitter_like.R
import com.example.twitter_like.data.model.conversation.Conversation
import com.example.twitter_like.views.view_holders.home_vh.ConversationRvViewHolder

class ConversationRvAdapter (
    private val conversations: List<Conversation>,
    private val onConversationClick: (Conversation) -> Unit
    ) : RecyclerView.Adapter<ConversationRvViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversationRvViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.conversation_item, parent, false)
            return ConversationRvViewHolder(view)
        }

        override fun onBindViewHolder(holder: ConversationRvViewHolder, position: Int) {
            val conversationData = conversations[position]
            holder.title.text = conversationData.conversationsUsers
                .joinToString(",") { it.users.username }
            holder.itemView.setOnClickListener {
                onConversationClick(conversationData)
            }
        }

        override fun getItemCount(): Int = conversations.size
}