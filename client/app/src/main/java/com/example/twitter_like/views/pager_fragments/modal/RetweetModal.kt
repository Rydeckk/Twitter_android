package com.example.twitter_like.views.pager_fragments.modal

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.twitter_like.R

class RetweetModal : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())
        val view = LayoutInflater.from(context).inflate(R.layout.modal_retweet, null)
        dialog.setContentView(view)

        val repostButton = view.findViewById<Button>(R.id.repost_button)
        val replyButton = view.findViewById<Button>(R.id.reply_button)

        repostButton.setOnClickListener {
            dismiss() // Pour l'instant, ferme juste la modal
        }

        replyButton.setOnClickListener {
            dismiss() // Pour l'instant, ferme juste la modal
        }

        return dialog
    }
}
