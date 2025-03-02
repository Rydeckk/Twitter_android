package com.example.twitter_like.pages

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.auth0.android.jwt.JWT
import com.example.twitter_like.data.model.user.User
import com.example.twitter_like.data.request.follow.FollowRequest
import com.example.twitter_like.data.request.follow.UnfollowRequest
import com.example.twitter_like.databinding.ProfileFragmentBinding
import com.example.twitter_like.network.callback.GenericCallback
import com.example.twitter_like.pages.interfaces.ProfilePagerHandler
import com.example.twitter_like.repositories.FollowRepository
import com.example.twitter_like.repositories.UserRepository
import com.example.twitter_like.viewmodel.FollowViewModel
import com.example.twitter_like.viewmodel.UserViewModel
import com.example.twitter_like.viewmodel.factories.FollowViewModelFactory
import com.example.twitter_like.viewmodel.factories.UserViewModelFactory
import com.example.twitter_like.views.ProfilePagerUserDetailAdapter
import com.example.twitter_like.views.pager_fragments.dialog.FollowersDialogFragment
import com.example.twitter_like.views.pager_fragments.dialog.FollowingsDialogFragment
import com.example.twitter_like.views.pager_fragments.dialog.UpdateUserDialogFragment
import com.example.twitter_like.views.pager_fragments.homePage.AllTweetFragment.Companion.USER_ID_EXTRA
import com.google.android.material.tabs.TabLayoutMediator

class UserDetailActivity : AppCompatActivity(), ProfilePagerHandler {
    private lateinit var profileViewPager: ViewPager2
    private lateinit var binding: ProfileFragmentBinding

    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory(UserRepository(this))
    }

    private val followViewModel: FollowViewModel by viewModels {
        FollowViewModelFactory(FollowRepository(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProfileFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val userId = intent.getStringExtra(USER_ID_EXTRA)
        if (userId != null) {
            fetchUser()
        } else {
            finish()
        }
    }

    private fun profileViewHolder(user: User) {
        val token = getToken() ?: return
        val jwt = JWT(token)
        val userId = jwt.subject ?: return
        val userDetailId = intent.getStringExtra(USER_ID_EXTRA) ?: return
        binding.apply {
            profileFragmentBack.setOnClickListener {
                finish()
            }
            openFollowersDialog.setOnClickListener {
                showFollowersDialog(false)
            }
            openFollowingsDialog.setOnClickListener {
                showFollowersDialog(true)
            }
            profileUpdateDialog.text =
                if (userDetailId == userId) "Modifier" else if (user.isUserFollowing == true) "Unfollow" else "Follow"
            profileUpdateDialog.setOnClickListener {
                if (userDetailId == userId) {
                    showUpdateUserDialog()
                } else {
                    if (user.isUserFollowing == true) {
                        unfollowUser(userDetailId)
                    } else {
                        followUser(userDetailId)
                    }
                }
            }
            val adapter = ProfilePagerUserDetailAdapter(this@UserDetailActivity, userDetailId)
            profileViewPage.adapter = adapter
            TabLayoutMediator(tabProfileLayout, profileViewPage) { tab, position ->
                tab.text = when (position) {
                    0 -> "Posts"
                    1 -> "Replies"
                    2 -> "Media"
                    3 -> "Likes"
                    else -> null
                }
            }.attach()
            profileFirstnameLastname.text = "${user.firstname} ${user.lastname}"
            profileUsername.text = "@${user.username}"
            profileBiography.text = user.biography
            openFollowersDialog.text = user.followedByCount.toString().plus(" Abonnements")
            openFollowingsDialog.text = user.followingCount.toString().plus(" Abonnées")
        }
    }

    private fun showUpdateUserDialog() {
        val editUserDialogFragment = UpdateUserDialogFragment() { fetchUser() }
        editUserDialogFragment.show(supportFragmentManager, "EditUserDialog")
    }

    private fun fetchUser() {
        intent.getStringExtra(USER_ID_EXTRA)?.let {
            userViewModel.getUserById(it, object : GenericCallback<User> {
                override fun onSuccess(data: User) {
                    profileViewHolder(data)
                }

                override fun onError(error: String) {
                    TODO("Not yet implemented")
                }
            })
        }
    }

    private fun followUser(followingId: String) {
        followViewModel.followUser(FollowRequest(followingId)) {
            fetchUser()
        }
    }

    private fun unfollowUser(followingId: String) {
        followViewModel.unfollowUser(UnfollowRequest(followingId)) {
            fetchUser()
        }
    }

    private fun showFollowersDialog(isFollowers: Boolean) {
        val dialogFragment =
            if (isFollowers) FollowersDialogFragment(intent.getStringExtra(USER_ID_EXTRA)) {
                fetchUser()
            } else FollowingsDialogFragment(intent.getStringExtra(USER_ID_EXTRA)) {
                fetchUser()
            }
        dialogFragment.show(supportFragmentManager, "FollowsDialog")
    }

    override fun displayPosts() {
        profileViewPager.currentItem = 0;
    }

    override fun displayReplies() {
        profileViewPager.currentItem = 1;
    }

    override fun displayMedias() {
        profileViewPager.currentItem = 2;
    }

    override fun displayLikes() {
        profileViewPager.currentItem = 3;
    }

    private fun getToken(): String? {
        val sharedPreferences =
            this.getSharedPreferences("MY_APP_SHARED_PREFS", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("token", null)
        return token
    }
}