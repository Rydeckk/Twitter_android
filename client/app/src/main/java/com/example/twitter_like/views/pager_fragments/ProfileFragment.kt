package com.example.twitter_like.views.pager_fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.auth0.android.jwt.JWT
import com.example.twitter_like.R
import com.example.twitter_like.data.model.user.User
import com.example.twitter_like.network.callback.GenericCallback
import com.example.twitter_like.pages.interfaces.ProfilePagerHandler
import com.example.twitter_like.repositories.UserRepository
import com.example.twitter_like.viewmodel.UserViewModel
import com.example.twitter_like.viewmodel.factories.UserViewModelFactory
import com.example.twitter_like.views.ProfilePagerAdapter
import com.example.twitter_like.views.pager_fragments.dialog.FollowersDialogFragment
import com.example.twitter_like.views.pager_fragments.dialog.FollowingsDialogFragment
import com.example.twitter_like.views.pager_fragments.dialog.UpdateUserDialogFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class ProfileFragment : Fragment(), ProfilePagerHandler {
    private lateinit var profileViewPager: ViewPager2

    private lateinit var openFollowersDialog: TextView
    private lateinit var openFollowingsDialog: TextView


    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory(UserRepository(requireContext()))
    }

    companion object {
        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.profile_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchUser(view)
        view.findViewById<Button>(R.id.profile_fragment_back).visibility = View.GONE
        openFollowersDialog = view.findViewById(R.id.open_followers_dialog)
        openFollowingsDialog = view.findViewById(R.id.open_followings_dialog)

        openFollowersDialog.setOnClickListener {
            showFollowersDialog(false, view)
        }

        openFollowingsDialog.setOnClickListener {
            showFollowersDialog(true, view)
        }

        view.findViewById<Button>(R.id.profile_update_dialog).setOnClickListener {
            showUpdateUserDialog(view)
        }

        val tabLayout = view.findViewById<TabLayout>(R.id.tab_profile_layout)
        val viewPager = view.findViewById<ViewPager2>(R.id.profile_view_page)
        val adapter = ProfilePagerAdapter(this)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Posts"
                1 -> "Replies"
                2 -> "Media"
                3 -> "Likes"
                else -> null
            }
        }.attach()
    }

    private fun profileViewHolder(user: User, view: View) {
        view.findViewById<TextView>(R.id.profile_firstname_lastname).text =
            "${user.firstname} ${user.lastname}"
        view.findViewById<TextView>(R.id.profile_username).text = "@".plus(user.username)
        view.findViewById<TextView>(R.id.open_followers_dialog).text =
            user.followedByCount.toString().plus(" Abonnements")
        view.findViewById<TextView>(R.id.open_followings_dialog).text =
            user.followingCount.toString().plus(" Abonnées")
        view.findViewById<TextView>(R.id.profile_biography).text = user.biography
    }

    private fun getToken(): String? {
        val sharedPreferences =
            requireContext().getSharedPreferences("MY_APP_SHARED_PREFS", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("token", null)
        return token
    }

    private fun fetchUser(view: View) {
        val token = getToken() ?: return
        val jwt = JWT(token)
        val userId = jwt.subject ?: return

        userViewModel.getUserById(userId, object : GenericCallback<User> {
            override fun onSuccess(data: User) {
                profileViewHolder(data, view)
            }

            override fun onError(error: String) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun showFollowersDialog(isFollowers: Boolean, view: View) {
        val dialogFragment =
            if (isFollowers) FollowersDialogFragment() {
                fetchUser(view)
            } else FollowingsDialogFragment() {
                fetchUser(view)
            }
        dialogFragment.show(parentFragmentManager, "FollowsDialog")
    }

    private fun showUpdateUserDialog(view: View) {
        val editUserDialogFragment = UpdateUserDialogFragment() { fetchUser(view) }
        editUserDialogFragment.show(parentFragmentManager, "EditUserDialog")
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
}