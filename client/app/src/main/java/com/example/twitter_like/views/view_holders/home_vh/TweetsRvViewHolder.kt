package com.example.twitter_like.views.view_holders.home_vh

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.twitter_like.R
import androidx.recyclerview.widget.RecyclerView

class TweetsRvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    // val profilePicture: ImageView = itemView.findViewById(R.id.tweet_profile_picture)
    val fullname = itemView.findViewById<TextView>(R.id.tweet_firstname_lastname)
    val username = itemView.findViewById<TextView>(R.id.tweet_username)
    val date = itemView.findViewById<TextView>(R.id.tweet_publication_date)
    val content = itemView.findViewById<TextView>(R.id.tweet_content)

    val tweetMainPart = itemView.findViewById<LinearLayout>(R.id.tweet_main_part)

    val commentCount: TextView = itemView.findViewById(R.id.tweet_comment_count)
    val retweetCount: TextView = itemView.findViewById(R.id.tweet_retweet_count)
    val likeCount: TextView = itemView.findViewById(R.id.tweet_like_count)
    val profilePicture: ImageView = itemView.findViewById(R.id.tweet_profile_picture)

}