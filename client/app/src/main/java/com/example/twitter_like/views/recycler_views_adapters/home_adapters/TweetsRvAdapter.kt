package com.example.twitter_like.views.recycler_views_adapters.home_adapters

import android.content.Context
import android.graphics.Color
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.auth0.android.jwt.JWT
import com.example.twitter_like.R
import com.example.twitter_like.data.model.like.Like
import com.example.twitter_like.data.model.retweet.RetweetType
import com.example.twitter_like.data.model.tweet.Tweet
import com.example.twitter_like.utils.formatDate
import com.example.twitter_like.views.view_holders.home_vh.TweetsRvViewHolder

class TweetsRvAdapter(
    private val context: Context,
    private val tweets: List<Tweet>,
    private val onLikeClick: (String) -> Unit,
    private val onUnlikeClick: (String, String) -> Unit,
    private val onTweetClick: (String) -> Unit,
    private val onUserProfileClick: (String) -> Unit,
    private val onCommentTweetClick: (String) -> Unit,
    private val onRetweetClick: (String) -> Unit
) :
    RecyclerView.Adapter<TweetsRvViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetsRvViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tweet, parent, false)

        return TweetsRvViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tweets.size
    }

    private fun getToken(): String? {
        val sharedPreferences =
            context.getSharedPreferences("MY_APP_SHARED_PREFS", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("token", null)
        return token
    }

    override fun onBindViewHolder(holder: TweetsRvViewHolder, position: Int) {
        val token = getToken() ?: return
        val jwt = JWT(token)
        val userId = jwt.subject ?: return
        val tweetPosition = this.tweets[position]

        val tweetData =
            if (tweetPosition.retweet != null && tweetPosition.retweet.type == RetweetType.REPOST) tweetPosition.retweet.parentTweet else tweetPosition

        holder.profilePicture.setOnClickListener {
            onUserProfileClick(tweetData.userId)
        }
        holder.fullname.text = "${tweetData.users.firstname} ${tweetData.users.lastname}"
        holder.username.text = "@${tweetData.users.username}"
        holder.date.text = formatDate(tweetData.createdAt)
        holder.content.text = tweetData.content
        holder.commentCount.text = tweetData.tweetComments.size.toString()
        holder.retweetCount.text = tweetData.tweetRetweets.size.toString()
        holder.likeCount.text = tweetData.like.size.toString()


        if (tweetPosition.comment != null) {
            holder.toResponseToLayout.visibility = View.VISIBLE
            holder.toResponseTo.text =
                "En réponse à @${tweetPosition.comment.parentTweet.users.username}"
        }

        if (tweetPosition.retweet != null && tweetPosition.retweet.type == RetweetType.REPOST) {
            holder.retweetInfoLayout.visibility = View.VISIBLE

            if (tweetPosition.userId != userId) {
                holder.retweetInfo.text = "${tweetPosition.users.username} à reposté"
            }

        }

        if (tweetPosition.retweet != null && tweetPosition.retweet.type == RetweetType.REPLY) {
            holder.parentTweetRetweetLayout.visibility = View.VISIBLE

            holder.parentFullname.text =
                "${tweetPosition.retweet.parentTweet.users.firstname} ${tweetPosition.retweet.parentTweet.users.lastname}"
            holder.parentUsername.text = "@${tweetPosition.retweet.parentTweet.users.username}"
            holder.parentTweetDate.text = formatDate(tweetPosition.retweet.parentTweet.createdAt)
            holder.parentTweetContent.text = tweetPosition.retweet.parentTweet.content

            holder.parentNavigation.setOnClickListener {
                onTweetClick(tweetPosition.retweet.parentTweet.id)
            }
            holder.parentProfilePicture.setOnClickListener {
                onUserProfileClick(tweetPosition.retweet.parentTweet.userId)
            }

        }


        if (tweetData.like.any { it -> it.tweetId == tweetData.id && it.userId == userId }) {
            holder.likeIcon.setColorFilter(Color.RED)
            holder.likeIcon.setOnClickListener {
                val foundLike =
                    tweetData.like.find { like: Like -> like.tweetId == tweetData.id && like.userId == userId }
                if (foundLike != null) {
                    onUnlikeClick(
                        tweetData.id,
                        foundLike.id
                    )
                }
            }
        } else {
            holder.likeIcon.setColorFilter(Color.BLACK)
            holder.likeIcon.setOnClickListener {
                onLikeClick(tweetData.id)
            }
        }

        holder.commentIcon.setOnClickListener {
            onCommentTweetClick(tweetData.id)
        }

        holder.retweetIcon.setOnClickListener {
            onRetweetClick(tweetData.id)
        }
        holder.tweetDetailNavigation.setOnClickListener { onTweetClick(tweetData.id) }
    }
}