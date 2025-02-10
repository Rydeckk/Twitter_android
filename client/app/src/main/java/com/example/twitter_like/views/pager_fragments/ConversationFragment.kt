package com.example.twitter_like.views.pager_fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.twitter_like.R
import com.example.twitter_like.data.model.conversation.Conversation
import com.example.twitter_like.views.ConversationAdapter

class ConversationFragment: Fragment(R.layout.conversation_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val conversations = listOf(
            Conversation(0, "Alice", "Salut !", System.currentTimeMillis()),
            Conversation(1,"Bob", "On se voit demain ?", System.currentTimeMillis() - 60000),
            Conversation(2,"Charlie", "Merci pour hier.", System.currentTimeMillis() - 3600000)
        )

        val adapter = ConversationAdapter(conversations)
        recyclerView.adapter = adapter
    }
}