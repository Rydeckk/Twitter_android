package com.example.twitter_like.views.pager_fragments.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.auth0.android.jwt.JWT
import com.example.twitter_like.R
import com.example.twitter_like.data.model.follow.Follows
import com.example.twitter_like.data.request.follow.FollowRequest
import com.example.twitter_like.data.request.follow.UnfollowRequest
import com.example.twitter_like.network.callback.GenericCallback
import com.example.twitter_like.repositories.FollowRepository
import com.example.twitter_like.viewmodel.FollowViewModel
import com.example.twitter_like.viewmodel.factories.FollowViewModelFactory
import com.example.twitter_like.views.recycler_views_adapters.home_adapters.FollowsRvAdapter

class FollowersDialogFragment(private val callback: () -> Unit) : DialogFragment() {

    private lateinit var followersRv: RecyclerView

    private val followViewModel: FollowViewModel by viewModels {
        FollowViewModelFactory(FollowRepository(requireContext()))
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_followers)
        dialog.findViewById<TextView>(R.id.follow_dialog_title).text = "Abonnées"
        fetchFollowers(dialog)
        return dialog
    }


    private fun setUpFollowersRv(followings: List<Follows>, dialog: Dialog) {
        followersRv = dialog.findViewById(R.id.user_rv)
        this.followersRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        followersRv.adapter =
            FollowsRvAdapter(requireContext(), false, followings, onFollowClick = { followingId ->
                followUser(followingId, dialog)
            }, onUnfollowClick = { followingId ->
                unfollowUser(followingId, dialog)
            })
    }

    private fun followUser(followingId: String, dialog: Dialog) {
        followViewModel.followUser(FollowRequest(followingId)) {
            fetchFollowers(dialog)
            callback()
        }
    }

    private fun unfollowUser(followingId: String, dialog: Dialog) {
        followViewModel.unfollowUser(UnfollowRequest(followingId)) {
            fetchFollowers(dialog)
            callback()
        }
    }

    private fun getToken(): String? {
        val sharedPreferences =
            requireContext().getSharedPreferences("MY_APP_SHARED_PREFS", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("token", null)
        return token
    }

    private fun fetchFollowers(dialog: Dialog) {
        val token = getToken() ?: return
        val jwt = JWT(token)
        val userId = jwt.subject ?: return

        followViewModel.getUserFollowers(userId, object : GenericCallback<List<Follows>> {
            override fun onSuccess(data: List<Follows>) {
                setUpFollowersRv(data, dialog)
            }

            override fun onError(error: String) {
                TODO("Not yet implemented")
            }
        })
    }
}