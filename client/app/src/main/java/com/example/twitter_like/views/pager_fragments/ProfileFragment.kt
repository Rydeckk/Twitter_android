package com.example.twitter_like.views.pager_fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
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
import com.example.twitter_like.views.pager_fragments.dialog.FollowsDialogFragment
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

        openFollowersDialog = view.findViewById(R.id.open_followers_dialog)
        openFollowingsDialog = view.findViewById(R.id.open_followings_dialog)

        openFollowersDialog.setOnClickListener {
            showFollowersDialog(false)
        }

        openFollowingsDialog.setOnClickListener {
            showFollowersDialog(true)
        }

        view.findViewById<Button>(R.id.profile_update_dialog).setOnClickListener {
            showUpdateUserDialog(view)
        }

        val tabLayout = view.findViewById<TabLayout>(R.id.tab_profile_layout)
        val viewPager = view.findViewById<ViewPager2>(R.id.profile_view_page)
        val adapter = ProfilePagerAdapter(this)
        viewPager.adapter = adapter

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val position = tab?.position ?: return
                updateNavigation(position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

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
        view.findViewById<TextView>(R.id.profile_firstname_lastname).text = user.username
        view.findViewById<TextView>(R.id.profile_username).text = "@".plus(user.username)
        view.findViewById<TextView>(R.id.open_followers_dialog).text =
            user.followedByCount.toString().plus(" Abonnements")
        view.findViewById<TextView>(R.id.open_followings_dialog).text =
            user.followingCount.toString().plus(" Abonnées")
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

    private fun showFollowersDialog(isFollowers: Boolean) {
        val dialogFragment = FollowsDialogFragment(isFollowers)
        dialogFragment.show(parentFragmentManager, "FollowsDialog")
    }

    private fun showUpdateUserDialog(view: View) {
        val editUserDialogFragment = UpdateUserDialogFragment() { fetchUser(view) }
        editUserDialogFragment.show(parentFragmentManager, "EditUserDialog")
    }

    private fun updateNavigation(position: Number) {
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.profile_fragment_navigation) as? NavHostFragment

        val navController = navHostFragment?.navController
            ?: view?.findViewById<FragmentContainerView>(R.id.profile_fragment_navigation)
                ?.getFragment<NavHostFragment>()?.navController

        if (navController == null) {
            return
        }

        if (position == 0) {
            navController.setGraph(R.navigation.user_tweets_navigation)
        }

        if (position == 3) {
            navController.setGraph(R.navigation.likes_tweets_navigation)
        }
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