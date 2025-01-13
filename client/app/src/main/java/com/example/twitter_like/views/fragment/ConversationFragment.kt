package com.example.twitter_like.views.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.twitter_like.R
import com.example.twitter_like.models.ConversationModel
import com.example.twitter_like.views.ConversationAdapter
import com.example.twitter_like.viewmodel.MainViewModel

class ConversationFragment: Fragment(R.layout.conversation_fragment) {
    private lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val conversations = listOf(
            ConversationModel(0, "Alice", "Salut !", System.currentTimeMillis()),
            ConversationModel(1,"Bob", "On se voit demain ?", System.currentTimeMillis() - 60000),
            ConversationModel(2,"Charlie", "Merci pour hier.", System.currentTimeMillis() - 3600000)
        )

        val adapter = ConversationAdapter(conversations)
        recyclerView.adapter = adapter
    }
}