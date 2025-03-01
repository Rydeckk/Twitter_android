package com.example.twitter_like.views.recycler_views_adapters.home_adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.twitter_like.R
import androidx.recyclerview.widget.RecyclerView
import com.auth0.android.jwt.JWT
import com.example.twitter_like.data.model.follow.Follows
import com.example.twitter_like.views.view_holders.home_vh.FollowsRvViewHolder

class FollowsRvAdapter(
    private val context: Context,
    private var isFollowing: Boolean,
    private val follows: List<Follows>,
    private val onFollowClick: (String) -> Unit,
    private val onUnfollowClick: (String) -> Unit
) :
    RecyclerView.Adapter<FollowsRvViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowsRvViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user, parent, false)
        return FollowsRvViewHolder(view)
    }

    override fun getItemCount(): Int {
        return follows.size
    }

    private fun getToken(): String? {
        val sharedPreferences =
            context.getSharedPreferences("MY_APP_SHARED_PREFS", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("token", null)
        return token
    }

    override fun onBindViewHolder(holder: FollowsRvViewHolder, position: Int) {
        val token = getToken() ?: return
        val jwt = JWT(token)
        val userId = jwt.subject ?: return

        val followData = this.follows[position]
        holder.fullname.text = followData.user.username
        holder.username.text = followData.user.username
        holder.biography.text = followData.user.biography

        if (isFollowing) {
            holder.followUnfollowButton.text =
                if (userId == followData.followedById) "Unfollow" else "Follow"
            holder.followUnfollowButton.setOnClickListener {
                if (userId == followData.followedById) {
                    onUnfollowClick(followData.user.id)
                } else {
                    onFollowClick(followData.user.id)
                }
            }
        } else {
            holder.followUnfollowButton.text =
                if (followData.isUserAlsoFollowing == true) "Unfollow" else "Follow"

            holder.followUnfollowButton.setOnClickListener {
                if (followData.isUserAlsoFollowing == true) {
                    onUnfollowClick(followData.user.id)
                } else {
                    onFollowClick(followData.user.id)
                }
            }
        }
    }
}