package com.example.twitter_like.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.twitter_like.R
import com.example.twitter_like.data.model.conversation.Conversation
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ConversationAdapter (
    private val conversations: List<Conversation>
    ) : RecyclerView.Adapter<ConversationAdapter.ConversationViewHolder>() {

        inner class ConversationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            private val title = view.findViewById<TextView>(R.id.tvTitle)
            private val lastMessage = view.findViewById<TextView>(R.id.tvLastMessage)
            private val timestamp = view.findViewById<TextView>(R.id.tvTimestamp)

            fun bind(conversation: Conversation) {
                title.text = conversation.title
                lastMessage.text = conversation.lastMessage
                timestamp.text = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date(conversation.timestamp))
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversationViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.conversation_item, parent, false)
            return ConversationViewHolder(view)
        }

        override fun onBindViewHolder(holder: ConversationViewHolder, position: Int) {
            holder.bind(conversations[position])
        }

        override fun getItemCount(): Int = conversations.size
}