package com.example.twitter_like.views.pager_fragments.profilePage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.twitter_like.R
import com.example.twitter_like.data.model.retweet.RetweetType
import com.example.twitter_like.data.model.tweet.Tweet
import com.example.twitter_like.data.request.like.UnlikeRequest
import com.example.twitter_like.data.request.retweet.RetweetRequest
import com.example.twitter_like.network.callback.GenericCallback
import com.example.twitter_like.pages.TweetDetailActivity
import com.example.twitter_like.pages.UserDetailActivity
import com.example.twitter_like.repositories.TweetRepository
import com.example.twitter_like.viewmodel.TweetViewModel
import com.example.twitter_like.viewmodel.factories.TweetViewModelFactory
import com.example.twitter_like.views.pager_fragments.modal.CommentTweetModal
import com.example.twitter_like.views.pager_fragments.modal.ReplyTweetModal
import com.example.twitter_like.views.pager_fragments.modal.RetweetModal
import com.example.twitter_like.views.recycler_views_adapters.home_adapters.TweetsRvAdapter

class RetweetsPageFragment : Fragment() {
    private lateinit var tweetsRv: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private var userDetailId: String? = null

    companion object {
        fun newInstance(userDetailId: String?): RetweetsPageFragment {
            val fragment = RetweetsPageFragment()
            val args = Bundle()
            args.putString(PostsPageFragment.USER_ID_EXTRA, userDetailId)
            fragment.arguments = args
            return fragment
        }

        const val TWEET_ID_EXTRA = "tweet_id"
        const val USER_ID_EXTRA = "user_id"
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
        arguments?.let {
            userDetailId = it.getString(PostsPageFragment.USER_ID_EXTRA)
        }
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
        }, onUserProfileClick = { userId ->
            navigateToUserDetail(userId)
        }, onCommentTweetClick = { tweetId ->
            commentTweet(tweetId, fragmentView)
        }, onRetweetClick = { tweetId ->
            retweetTweet(tweetId)
        })
    }

    private fun retweetTweet(tweetId: String) {
        val modal = RetweetModal.newInstance(tweetId, onRetweetRepostTweetClick = {
            onRetweetRepost(tweetId)
        }, onRetweetReplyTweetClick = {
            replyTweetModal(tweetId)
        })
        modal.show(
            parentFragmentManager,
            "RetweetModal"
        )
    }

    private fun replyTweetModal(parentTweetId: String) {
        val modal = ReplyTweetModal.newInstance { content ->
            onRetweetReply(parentTweetId, content)
        }
        modal.show(
            parentFragmentManager,
            "ReplyModal"
        )
    }


    private fun commentTweet(tweetId: String, fragmentView: View) {
        val modal = CommentTweetModal.newInstance(tweetId) {
            fetchData(fragmentView)
        }
        modal.show(
            parentFragmentManager,
            "CommentTweetModal"
        )
    }

    private fun onRetweetRepost(parentTweetId: String) {
        tweetViewModel.retweetTweet(RetweetRequest("", RetweetType.REPOST, parentTweetId)) {
            fetchData(requireView())
        }
    }

    private fun onRetweetReply(parentTweetId: String, content: String) {
        tweetViewModel.retweetTweet(RetweetRequest(content, RetweetType.REPLY, parentTweetId)) {
            fetchData(requireView())
        }
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
            putExtra(PostsPageFragment.TWEET_ID_EXTRA, tweetId)
        }
        startActivity(intent)
    }

    private fun navigateToUserDetail(userId: String) {
        val intent = Intent(requireContext(), UserDetailActivity::class.java).apply {
            putExtra(PostsPageFragment.USER_ID_EXTRA, userId)
        }
        startActivity(intent)
    }

    private fun fetchData(fragmentView: View) {
        tweetViewModel.getRetweetsTweets(userDetailId, object : GenericCallback<List<Tweet>> {
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