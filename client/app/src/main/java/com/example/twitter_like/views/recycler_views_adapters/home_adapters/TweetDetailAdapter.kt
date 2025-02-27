package com.example.twitter_like.views.recycler_views_adapters.home_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.twitter_like.R
import com.example.twitter_like.data.model.tweet.Tweet
import com.example.twitter_like.utils.formatDate
import com.example.twitter_like.views.view_holders.home_vh.TweetDetailViewHolder

class TweetDetailAdapter(
    private val tweets: List<Tweet>,
    private val onTweetClick: (String) -> Unit
) : RecyclerView.Adapter<TweetDetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetDetailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tweet, parent, false)
        return TweetDetailViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tweets.size
    }

    override fun onBindViewHolder(holder: TweetDetailViewHolder, position: Int) {
        val tweetData = this.tweets[position]
        holder.fullname.text = tweetData.users.username
        holder.username.text = tweetData.users.username
        holder.date.text = formatDate(tweetData.createdAt)
        holder.content.text = tweetData.content
        holder.commentCount.text = "23K"
        holder.retweetCount.text = "47K"
        holder.likeCount.text = "321K"
        holder.tweetDetailNavigation.setOnClickListener { onTweetClick(tweetData.id) }
    }

}