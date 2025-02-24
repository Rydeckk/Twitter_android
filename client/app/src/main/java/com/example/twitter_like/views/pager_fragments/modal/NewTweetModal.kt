package com.example.twitter_like.views.pager_fragments.modal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.twitter_like.R
import com.example.twitter_like.data.request.tweet.TweetResponse
import com.example.twitter_like.network.callback.GenericCallback
import com.example.twitter_like.repositories.TweetRepository
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NewTweetModal : BottomSheetDialogFragment() {

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
        val tweetRepository = TweetRepository(requireContext())

        tweetButton.setOnClickListener {
            val tweetText = tweetInput.text.toString().trim()
            if (tweetText.isNotEmpty()) {
                tweetRepository.sendTweet(tweetText, object : GenericCallback<TweetResponse> {
                    override fun onSuccess(data: TweetResponse) {
                        Toast.makeText(requireContext(), "Tweet envoyé !", Toast.LENGTH_SHORT)
                            .show()
                        dismiss()
                    }

                    override fun onError(error: String) {
                        Toast.makeText(requireContext(), "Erreur : $error", Toast.LENGTH_SHORT)
                            .show()
                    }
                })

            } else {
                Toast.makeText(
                    requireContext(),
                    "Le tweet ne peut pas être vide",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}