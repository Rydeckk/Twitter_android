package com.example.twitter_like.views.recycler_views_adapters.home_adapters

import android.view.ViewGroup
import android.util.Log
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.example.twitter_like.R
import com.example.twitter_like.data.model.tweet.Tweet
import com.example.twitter_like.utils.formatDate
import com.example.twitter_like.views.view_holders.home_vh.TweetsRvViewHolder

class TweetsRvAdapter (private val tweets: List<Tweet>): RecyclerView.Adapter<TweetsRvViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetsRvViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tweet, parent, false)

        return TweetsRvViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tweets.size
    }

    override fun onBindViewHolder(holder: TweetsRvViewHolder, position: Int) {
        val tweetData = this.tweets[position]
        holder.fullname.text = tweetData.user.username
        holder.username.text = tweetData.user.username
        holder.date.text = formatDate(tweetData.createdAt)
        holder.content.text = tweetData.content

        holder.tweetMainPart.setOnClickListener {
            Log.d("Tweet card", "Tweet card clicked")
        }
    }
}