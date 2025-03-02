package com.example.twitter_like.views.pager_fragments.modal

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.twitter_like.R

class RetweetModal(
    private val onRetweetRepostTweetClick: () -> Unit,
    private val onRetweetReplyTweetClick: () -> Unit
) : DialogFragment() {

    companion object {
        private const val ARG_TWEET_ID = "tweet_id"

        fun newInstance(
            tweetId: String, onRetweetRepostTweetClick: () -> Unit,
            onRetweetReplyTweetClick: () -> Unit
        ): RetweetModal {
            val fragment = RetweetModal(onRetweetRepostTweetClick, onRetweetReplyTweetClick)
            val args = Bundle()
            args.putString(ARG_TWEET_ID, tweetId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())

        dialog.setContentView(R.layout.modal_retweet)

        val repostButton = dialog.findViewById<Button>(R.id.repost_button)
        val replyButton = dialog.findViewById<Button>(R.id.reply_button)

        repostButton.setOnClickListener {
            onRetweetRepostTweetClick()
            dismiss()
        }

        replyButton.setOnClickListener {
            onRetweetReplyTweetClick()
            dismiss()
        }
        return dialog
    }


}
