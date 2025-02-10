package com.example.twitter_like.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.twitter_like.R
import com.example.twitter_like.data.model.message.Message
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MessageAdapter (
    private val messages: List<Message>
) : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    inner class MessageViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val content = view.findViewById<TextView>(R.id.message_text)
        private val timestamp = view.findViewById<TextView>(R.id.message_time)

        fun bind(message: Message) {
            content.text = message.content
            timestamp.text = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date(message.timestamp))
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MessageAdapter.MessageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.message_item, parent, false)
        return MessageViewHolder(view)
    }

    override fun getItemCount(): Int = messages.size

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(messages[position])
    }

}