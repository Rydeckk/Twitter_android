package com.example.twitter_like.views.pager_fragments.modal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.twitter_like.R
import com.example.twitter_like.data.request.comment.CommentRequest
import com.example.twitter_like.repositories.TweetRepository
import com.example.twitter_like.viewmodel.TweetViewModel
import com.example.twitter_like.viewmodel.factories.TweetViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CommentTweetModal(private val onCommentTweetClick: () -> Unit) : BottomSheetDialogFragment() {

    private val tweetViewModel: TweetViewModel by viewModels {
        TweetViewModelFactory(TweetRepository(requireContext()))
    }

    companion object {
        private const val ARG_TWEET_ID = "tweet_id"

        fun newInstance(tweetId: String, onCommentTweetClick: () -> Unit): CommentTweetModal {
            val fragment = CommentTweetModal(onCommentTweetClick)
            val args = Bundle()
            args.putString(ARG_TWEET_ID, tweetId)
            fragment.arguments = args
            return fragment
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
        val tweetId = arguments?.getString(ARG_TWEET_ID) ?: return
        val tweetInput = view.findViewById<EditText>(R.id.tweet_input)
        val tweetButton = view.findViewById<Button>(R.id.send_tweet_button)

        tweetButton.text = "Commenter"

        tweetButton.setOnClickListener {
            val tweetText = tweetInput.text.toString().trim()
            if (tweetText.isNotEmpty()) {
                postComment(tweetText, tweetId)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Le commentaire ne peut pas être vide",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun postComment(content: String, parentTweetId: String) {
        val data = CommentRequest(content, parentTweetId)
        tweetViewModel.commentTweet(data) {
            dismiss()
            onCommentTweetClick()
        }
    }

}