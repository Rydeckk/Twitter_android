package com.example.twitter_like.views.pager_fragments.modal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.twitter_like.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ReplyTweetModal(private val onReplyClick: (String) -> Unit) : BottomSheetDialogFragment() {
    companion object {
        fun newInstance(onReplyClick: (String) -> Unit): ReplyTweetModal {
            val fragment = ReplyTweetModal(onReplyClick)
            return fragment;
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.modal_new_tweet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tweetInput = view.findViewById<EditText>(R.id.tweet_input)
        val tweetButton = view.findViewById<Button>(R.id.send_tweet_button)

        tweetButton.text = "Reply"

        tweetButton.setOnClickListener {
            val tweetText = tweetInput.text.toString().trim()
            if (tweetText.isNotEmpty()) {
                onReplyClick(tweetText)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Le reply ne peut pas être vide",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}