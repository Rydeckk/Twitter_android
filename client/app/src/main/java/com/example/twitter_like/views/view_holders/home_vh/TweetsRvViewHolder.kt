package com.example.twitter_like.views.view_holders.home_vh

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.twitter_like.R
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class TweetsRvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val tweetDetailNavigation = itemView.findViewById<LinearLayout>(R.id.tweet_detail_navigation)
    val profilePicture: ImageView = itemView.findViewById(R.id.tweet_profile_picture)
    val fullname = itemView.findViewById<TextView>(R.id.tweet_firstname_lastname)
    val username = itemView.findViewById<TextView>(R.id.tweet_username)
    val date = itemView.findViewById<TextView>(R.id.tweet_publication_date)
    val content = itemView.findViewById<TextView>(R.id.tweet_content)
    val retweetInfoLayout = itemView.findViewById<LinearLayout>(R.id.tweet_retweet_response_layout)
    val retweetInfo = itemView.findViewById<TextView>(R.id.tweet_retweet_response)
    val toResponseToLayout = itemView.findViewById<LinearLayout>(R.id.tweet_comment_response_layout)
    val toResponseTo = itemView.findViewById<TextView>(R.id.tweet_comment_response)

    val commentCount: TextView = itemView.findViewById(R.id.tweet_comment_count)
    val retweetCount: TextView = itemView.findViewById(R.id.tweet_retweet_count)
    val likeCount: TextView = itemView.findViewById(R.id.tweet_like_count)
    val likeIcon: ImageView = itemView.findViewById(R.id.tweet_like_icon)
    val commentIcon: ImageView = itemView.findViewById(R.id.tweet_comment_icon)
    val retweetIcon: ImageView = itemView.findViewById(R.id.tweet_retweet_icon)

    val parentProfilePicture =
        itemView.findViewById<MaterialCardView>(R.id.parent_tweet_material_card_view)
    val parentNavigation = itemView.findViewById<LinearLayout>(R.id.parent_tweet_detail_navigation)

    val parentTweetRetweetLayout =
        itemView.findViewById<LinearLayout>(R.id.parent_tweet_retweet_layout)
    val parentFullname = itemView.findViewById<TextView>(R.id.parent_tweet_firstname_lastname)
    val parentUsername = itemView.findViewById<TextView>(R.id.parent_tweet_username)
    val parentTweetDate = itemView.findViewById<TextView>(R.id.parent_tweet_publication_date)
    val parentTweetContent = itemView.findViewById<TextView>(R.id.parent_tweet_content)

}