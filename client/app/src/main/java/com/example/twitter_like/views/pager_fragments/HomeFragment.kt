package com.example.twitter_like.views.pager_fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.twitter_like.viewmodel.TweetViewModel
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.twitter_like.viewmodel.factories.TweetViewModelFactory
import com.example.twitter_like.R
import com.example.twitter_like.data.model.tweet.Tweet
import com.example.twitter_like.network.callback.GenericCallback
import com.example.twitter_like.pages.ProfileActivity
import com.example.twitter_like.repositories.TweetRepository
import com.example.twitter_like.views.recycler_views_adapters.home_adapters.TweetsRvAdapter

class HomeFragment : Fragment() {
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var tweetsRv: RecyclerView

    private val tweetViewModel: TweetViewModel by viewModels {
        TweetViewModelFactory(TweetRepository(requireContext()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.home_fragment, container, false)
        this.swipeRefreshLayout = view.findViewById(R.id.tweet_rv_swipe_refresh_layout)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchData(view)
        setUpSwipeToRefreshListeners(view)

        val profilePicture = view.findViewById<com.google.android.material.imageview.ShapeableImageView>(R.id.profile_picture)
        profilePicture.setOnClickListener {
            val intent = Intent(requireContext(), ProfileActivity::class.java)
            startActivity(intent)
        }

    }

    private fun setUpTweetsRv(tweets: List<Tweet>, fragmentView: View) {
        this.tweetsRv = fragmentView.findViewById(R.id.tweet_rv)
        this.tweetsRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        this.tweetsRv.adapter = TweetsRvAdapter(tweets)
    }

    private fun fetchData(fragmentView: View) {
        tweetViewModel.fetchGlobalData(object : GenericCallback<List<Tweet>> {
            override fun onSuccess(data: List<Tweet>) {
                setUpTweetsRv(data, fragmentView)
                swipeRefreshLayout.isRefreshing = false
            }

            override fun onError(error: String) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun setUpSwipeToRefreshListeners(fragmentView: View) {
        this.swipeRefreshLayout.setOnRefreshListener {
            this.swipeRefreshLayout.isRefreshing = true
            tweetViewModel.fetchGlobalData(object : GenericCallback<List<Tweet>> {
                override fun onSuccess(data: List<Tweet>) {
                    setUpTweetsRv(data, fragmentView)
                    swipeRefreshLayout.isRefreshing = false
                }

                override fun onError(error: String) {
                    TODO("Not yet implemented")
                }
            })
        }
    }
    override fun onResume() {
        super.onResume()
        fetchData(requireView())
    }
}