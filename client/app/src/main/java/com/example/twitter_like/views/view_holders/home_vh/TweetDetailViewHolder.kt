package com.example.twitter_like.views.view_holders.home_vh

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.twitter_like.R

class TweetDetailViewHolder(itemView: View) :  RecyclerView.ViewHolder(itemView) {
    val tweetDetailNavigation = itemView.findViewById<LinearLayout>(R.id.tweet_detail_navigation)
    // val profilePicture: ImageView = itemView.findViewById(R.id.tweet_profile_picture)
    val fullname = itemView.findViewById<TextView>(R.id.tweet_firstname_lastname)
    val username = itemView.findViewById<TextView>(R.id.tweet_username)
    val date = itemView.findViewById<TextView>(R.id.tweet_publication_date)
    val content = itemView.findViewById<TextView>(R.id.tweet_content)

    val commentCount = itemView.findViewById<TextView>(R.id.tweet_comment_count)
    val retweetCount = itemView.findViewById<TextView>(R.id.tweet_retweet_count)
    val likeCount = itemView.findViewById<TextView>(R.id.tweet_like_count)
}