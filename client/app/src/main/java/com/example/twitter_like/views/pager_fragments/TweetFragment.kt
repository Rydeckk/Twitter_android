package com.example.twitter_like.views.pager_fragments

import android.annotation.SuppressLint
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
import com.example.twitter_like.pages.interfaces.PagerHandler
import com.example.twitter_like.repositories.GlobalDataRepository
import com.example.twitter_like.viewmodel.factories.TweetViewModelFactory
import com.example.twitter_like.R
import com.example.twitter_like.data.model.GlobalDataModel
import com.example.twitter_like.data.model.tweet.Tweet
import com.example.twitter_like.views.recycler_views_adapters.home_adapters.TweetsRvAdapter

class TweetFragment : Fragment() {

    private lateinit var _pagerHandler: PagerHandler
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var tweetsRv: RecyclerView


    private val tweetViewModel: TweetViewModel by viewModels {
        TweetViewModelFactory(GlobalDataRepository(), this)
    }

    companion object {
        fun newInstance(pageHandler: PagerHandler): TweetFragment {
            return TweetFragment().also {
                it._pagerHandler = pageHandler
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.tweet_rv, container, false)
        this.swipeRefreshLayout = view.findViewById(R.id.tweet_rv_swipe_refresh_layout)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchData(view)
        setUpSwipeToRefreshListeners()
    }

    private fun setUpPostsRv(tweets: List<Tweet>, fragmentView: View) {
        this.tweetsRv = fragmentView.findViewById(R.id.tweet_rv)
        this.tweetsRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        this.tweetsRv.adapter = TweetsRvAdapter(tweets)
    }

    private fun fetchData(fragmentView: View) {
        tweetViewModel.globalData.observe(viewLifecycleOwner) { data ->
            setUpPostsRv(getUserTweets(data), fragmentView)
            this.swipeRefreshLayout.isRefreshing = false
        }
        tweetViewModel.fetchGlobalData()
    }

    private fun getUserTweets(data: GlobalDataModel): List<Tweet> {
        return data.tweets
    }

    private fun setUpSwipeToRefreshListeners() {
        this.swipeRefreshLayout.setOnRefreshListener {
            this.tweetViewModel.fetchGlobalData()
        }
    }
}