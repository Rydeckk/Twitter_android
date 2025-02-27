package com.example.twitter_like.pages

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.twitter_like.data.model.tweet.Tweet
import com.example.twitter_like.databinding.TweetDetailBinding
import com.example.twitter_like.network.callback.GenericCallback
import com.example.twitter_like.repositories.TweetRepository
import com.example.twitter_like.viewmodel.TweetViewModel
import com.example.twitter_like.viewmodel.factories.TweetViewModelFactory
import com.example.twitter_like.views.pager_fragments.homePage.AllTweetFragment
import com.example.twitter_like.views.pager_fragments.homePage.AllTweetFragment.Companion.TWEET_ID_EXTRA
import com.example.twitter_like.views.recycler_views_adapters.home_adapters.TweetDetailAdapter


class TweetDetailActivity : AppCompatActivity() {
    private lateinit var binding: TweetDetailBinding
    private lateinit var tweetsRv: RecyclerView

    private val tweetViewModel: TweetViewModel by viewModels {
        TweetViewModelFactory(TweetRepository(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TweetDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tweetId = intent.getStringExtra(AllTweetFragment.TWEET_ID_EXTRA)

        if (tweetId != null) {
            fetchData()
        } else {

            finish()
        }

    }

    private fun displayTweetDetails(tweets: List<Tweet>) {
        Log.d("TweetDetailActivity", "RecyclerView: $binding.tweetDetailRv")
        binding.apply {
            tweetDetailBack.setOnClickListener {
                finish()
            }
            tweetDetailFirstnameLastname.text = "oui"
            tweetDetailContentComment.setText("Poster votre tweet")
            tweetDetailRv.layoutManager = LinearLayoutManager(this@TweetDetailActivity, LinearLayoutManager.VERTICAL, false)
            tweetDetailRv.adapter = TweetDetailAdapter(tweets) { tweetId ->
                navigateToTweetDetail(tweetId)
            }
            Log.d("TweetDetailActivity", "RecyclerView: ${tweetDetailRv.adapter?.itemCount}")
        }
    }

    private fun navigateToTweetDetail(tweetId: String) {
        val intent = Intent(this, TweetDetailActivity::class.java).apply {
            putExtra(TWEET_ID_EXTRA, tweetId)
        }
        startActivity(intent)
    }

    private fun fetchData() {
        tweetViewModel.getAllTweets(object : GenericCallback<List<Tweet>> {
            override fun onSuccess(data: List<Tweet>) {
                displayTweetDetails(data)
            }

            override fun onError(error: String) {
                TODO("Not yet implemented")
            }
        })
    }
}