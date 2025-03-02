package com.example.twitter_like.views.view_holders.home_vh

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.twitter_like.R

class FollowsRvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    // val profilePicture = itemView.findViewById(R.id.user_material_card_view)
    val fullname = itemView.findViewById<TextView>(R.id.user_firstname_lastname)
    val username = itemView.findViewById<TextView>(R.id.user_username)
    val biography = itemView.findViewById<TextView>(R.id.user_biography)
    val followUnfollowButton = itemView.findViewById<Button>(R.id.user_follow_unfollow)

}