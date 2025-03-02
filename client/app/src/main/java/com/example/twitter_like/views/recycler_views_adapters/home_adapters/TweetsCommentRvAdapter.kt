package com.example.twitter_like.views.recycler_views_adapters.home_adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.auth0.android.jwt.JWT
import com.example.twitter_like.R
import com.example.twitter_like.data.model.comment.Comment
import com.example.twitter_like.data.model.like.Like
import com.example.twitter_like.utils.formatDate
import com.example.twitter_like.views.pager_fragments.modal.CommentTweetModal
import com.example.twitter_like.views.pager_fragments.modal.RetweetModal
import com.example.twitter_like.views.view_holders.home_vh.TweetsRvViewHolder

class TweetsCommentRvAdapter(
    private val context: Context,
    private val comments: List<Comment>,
    private val onLikeClick: (String) -> Unit,
    private val onUnlikeClick: (String, String) -> Unit,
    private val onTweetClick: (String) -> Unit,
    private val onCommentTweetClick: (String) -> Unit,
    private val onRetweetClick: (String) -> Unit
) : RecyclerView.Adapter<TweetsRvViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetsRvViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tweet, parent, false)

        return TweetsRvViewHolder(view)
    }

    override fun getItemCount(): Int {
        return comments.size
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
        val commentTweet = this.comments[position]
        val tweetData = this.comments[position].tweet

        holder.fullname.text = commentTweet.users.username
        holder.username.text = commentTweet.users.username
        holder.date.text = formatDate(commentTweet.createdAt)
        holder.content.text = tweetData.content
        holder.commentCount.text = tweetData.tweetComments.size.toString()
        holder.retweetCount.text = tweetData.tweetRetweets.size.toString()
        holder.likeCount.text = tweetData.like.size.toString()

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