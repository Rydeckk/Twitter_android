package com.example.twitter_like.views.recycler_views_adapters.home_adapters

import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.twitter_like.R
import com.example.twitter_like.data.model.message.Message
import com.example.twitter_like.data.model.user.User
import com.example.twitter_like.utils.formatDateToHour
import com.example.twitter_like.views.view_holders.home_vh.MessageRvViewHolder

class MessageRvAdapter (
    private var messages: List<Message>,
    private var currentUser: User
) : RecyclerView.Adapter<MessageRvViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageRvViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.message_item, parent, false)
        return MessageRvViewHolder(view)
    }

    override fun getItemCount(): Int = messages.size

    override fun onBindViewHolder(holder: MessageRvViewHolder, position: Int) {
        val messageData = messages[position]
        holder.messageOwner.text = messageData.users.username
        holder.messageContent.text = messageData.message
        holder.dateMessage.text = formatDateToHour(messageData.createdAt)

        if (messageData.userId == currentUser.id) {
            holder.messageContainer.setBackgroundResource(R.drawable.background_my_message)
            holder.mainLayout.gravity = Gravity.END
        } else {
            holder.messageContent.setBackgroundResource(R.drawable.background_message)
            holder.mainLayout.gravity = Gravity.START
        }
    }
}