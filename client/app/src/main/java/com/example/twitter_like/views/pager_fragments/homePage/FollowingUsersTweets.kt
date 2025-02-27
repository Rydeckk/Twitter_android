package com.example.twitter_like.views.pager_fragments.homePage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.twitter_like.R
import com.example.twitter_like.data.model.tweet.Tweet
import com.example.twitter_like.data.request.like.UnlikeRequest
import com.example.twitter_like.network.callback.GenericCallback
import com.example.twitter_like.pages.TweetDetailActivity
import com.example.twitter_like.repositories.TweetRepository
import com.example.twitter_like.viewmodel.TweetViewModel
import com.example.twitter_like.viewmodel.factories.TweetViewModelFactory
import com.example.twitter_like.views.recycler_views_adapters.home_adapters.TweetDetailAdapter
import com.example.twitter_like.views.recycler_views_adapters.home_adapters.TweetsRvAdapter

class FollowingUsersTweets : Fragment() {
    private lateinit var tweetsRv: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    companion object {
        fun newInstance(): FollowingUsersTweets {
            return FollowingUsersTweets()
        }
        const val TWEET_ID_EXTRA = "tweet_id"
    }

    private val tweetViewModel: TweetViewModel by viewModels {
        TweetViewModelFactory(TweetRepository(requireContext()))
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
        setUpSwipeToRefreshListeners(view)

    }

    private fun setUpTweetsRv(tweets: List<Tweet>, fragmentView: View) {
        this.tweetsRv = fragmentView.findViewById(R.id.tweet_rv)
        this.tweetsRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        this.tweetsRv.adapter = TweetsRvAdapter(requireContext(), tweets, onLikeClick = { tweetId ->
            likeTweet(tweetId)
        }, onUnlikeClick = { tweetId, likeId ->
            unlikeTweet(tweetId, likeId)
        }, onTweetClick = { tweetId ->
            navigateToTweetDetail(tweetId)
        })
    }

    private fun likeTweet(tweetId: String) {
        tweetViewModel.likeTweet(tweetId) {
            fetchData(requireView())
        }
    }

    private fun unlikeTweet(tweetId: String, likeId: String) {
        val unlikeRequest = UnlikeRequest(tweetId, likeId)
        tweetViewModel.unlikeTweet(unlikeRequest) {
            fetchData(requireView())
        }
    }

    private fun navigateToTweetDetail(tweetId: String) {
        val intent = Intent(requireContext(), TweetDetailActivity::class.java).apply {
            putExtra(TWEET_ID_EXTRA, tweetId)
        }
        startActivity(intent)
    }

    private fun fetchData(fragmentView: View) {
        tweetViewModel.getFollowingUsersTweets(object : GenericCallback<List<Tweet>> {
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
            fetchData(fragmentView)
        }
    }

    override fun onResume() {
        super.onResume()
        fetchData(requireView())
    }
}