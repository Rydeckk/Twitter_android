package com.example.twitter_like.views.recycler_views_adapters.home_adapters

import android.content.Context
import android.graphics.Color
import android.view.ViewGroup
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.twitter_like.R
import com.example.twitter_like.data.model.tweet.Tweet
import com.example.twitter_like.utils.formatDate
import com.example.twitter_like.viewmodel.TweetViewModel
import com.example.twitter_like.views.pager_fragments.modal.NewTweetModal
import com.example.twitter_like.views.pager_fragments.modal.RetweetModal
import com.example.twitter_like.views.view_holders.home_vh.TweetsRvViewHolder

class TweetsRvAdapter(private val tweets: List<Tweet>,private val tweetViewModel: TweetViewModel) :
    RecyclerView.Adapter<TweetsRvViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetsRvViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tweet, parent, false)

        return TweetsRvViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tweets.size
    }

    override fun onBindViewHolder(holder: TweetsRvViewHolder, position: Int) {
        val tweetData = this.tweets[position]
        holder.fullname.text = tweetData.users.username
        holder.username.text = tweetData.users.username
        holder.date.text = formatDate(tweetData.createdAt)
        holder.content.text = tweetData.content
        holder.commentCount.text = tweetData.tweetComments.size.toString()
        holder.retweetCount.text = tweetData.tweetRetweets.size.toString()
        holder.likeCount.text = tweetData.like.size.toString()

        val sharedPreferences = holder.itemView.context.getSharedPreferences(
            "MY_APP_SHARED_PREFS", Context.MODE_PRIVATE
        )
        val token = sharedPreferences.getString("token", null) ?: return

        tweetViewModel.likedTweets.observeForever { likedTweets ->
            Log.d("LikeDebug", "Liked tweets: $likedTweets")
            if (likedTweets.contains(tweetData.id)) {
                holder.likeIcon.setColorFilter(Color.RED)
            } else {
                holder.likeIcon.setColorFilter(Color.BLACK)
            }
        }

        holder.likeIcon.setOnClickListener {
            val sharedPreferences = holder.itemView.context.getSharedPreferences(
                "MY_APP_SHARED_PREFS", Context.MODE_PRIVATE
            )
            val token = sharedPreferences.getString("token", null) ?: return@setOnClickListener

            if (tweetViewModel.likedTweets.value?.contains(tweetData.id) == true) {
                return@setOnClickListener
            }
            tweetViewModel.likeTweet(tweetData.id, token)
        }

        holder.commentIcon.setOnClickListener {
            val modal = NewTweetModal()
            modal.show((holder.itemView.context as AppCompatActivity).supportFragmentManager, "NewTweetModal")
        }

        holder.retweetIcon.setOnClickListener {
            val modal = RetweetModal()
            modal.show((holder.itemView.context as AppCompatActivity).supportFragmentManager, "RetweetModal")
        }
    }
}