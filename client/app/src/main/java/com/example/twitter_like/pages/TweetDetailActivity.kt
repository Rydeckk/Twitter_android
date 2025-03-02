package com.example.twitter_like.pages

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.auth0.android.jwt.JWT
import com.example.twitter_like.data.model.like.Like
import com.example.twitter_like.data.model.tweet.Tweet
import com.example.twitter_like.data.request.comment.CommentRequest
import com.example.twitter_like.data.request.like.UnlikeRequest
import com.example.twitter_like.databinding.TweetDetailBinding
import com.example.twitter_like.network.callback.GenericCallback
import com.example.twitter_like.repositories.TweetRepository
import com.example.twitter_like.utils.formatDate
import com.example.twitter_like.viewmodel.TweetViewModel
import com.example.twitter_like.viewmodel.factories.TweetViewModelFactory
import com.example.twitter_like.views.pager_fragments.homePage.AllTweetFragment.Companion.TWEET_ID_EXTRA
import com.example.twitter_like.views.pager_fragments.homePage.AllTweetFragment.Companion.USER_ID_EXTRA
import com.example.twitter_like.views.pager_fragments.modal.NewTweetModal
import com.example.twitter_like.views.pager_fragments.modal.RetweetModal
import com.example.twitter_like.views.recycler_views_adapters.home_adapters.TweetsCommentRvAdapter

class TweetDetailActivity : AppCompatActivity() {
    private lateinit var binding: TweetDetailBinding

    private val tweetViewModel: TweetViewModel by viewModels {
        TweetViewModelFactory(TweetRepository(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TweetDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tweetId = intent.getStringExtra(TWEET_ID_EXTRA)

        if (tweetId != null) {
            fetchData()
        } else {
            finish()
        }
    }

    private fun displayTweetDetails(tweet: Tweet) {
        val token = getToken() ?: return
        val jwt = JWT(token)
        val userId = jwt.subject ?: return
        binding.apply {
            tweetDetailBack.setOnClickListener {
                finish()
            }
            tweetDetailProfilePicture.setOnClickListener {
                navigateToUserDetail(tweet.userId)
            }
            tweetDetailCommentIcon.setOnClickListener {
                val modal = NewTweetModal()
                modal.show(supportFragmentManager, "NewTweetModal")
            }
            tweetDetailRetweetIcon.setOnClickListener {
                val modal = RetweetModal()
                modal.show(supportFragmentManager, "RetweetModal")
            }
            if (tweet.like.any { it -> it.tweetId == tweet.id && it.userId == userId }) {
                tweetDetailLikeIcon.setColorFilter(Color.RED)
                tweetDetailLikeIcon.setOnClickListener {
                    val foundLike =
                        tweet.like.find { like: Like -> like.userId == userId }
                    if (foundLike != null) {
                        unlikeTweet(
                            tweet.id,
                            foundLike.id
                        )
                    }
                }
            } else {
                tweetDetailLikeIcon.setColorFilter(Color.BLACK)
                tweetDetailLikeIcon.setOnClickListener {
                    likeTweet(tweet.id)
                }
            }
            tweetDetailResponse.setOnClickListener {
                val commentField = tweetDetailContentComment.text.toString().trim()
                if (commentField.isEmpty()) {
                    Toast.makeText(
                        this@TweetDetailActivity,
                        "Le champ ne doit pas être vide",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                postComment(commentField, tweet.id)
                tweetDetailContentComment.setText("")
            }
            tweetDetailCommentCount.text = tweet.tweetComments.size.toString()
            tweetDetailLikeCount.text = tweet.like.size.toString()
            tweetDetailRetweetCount.text = tweet.tweetRetweets.size.toString()
            tweetDetailFirstnameLastname.text = "${tweet.users.firstname} ${tweet.users.lastname}"
            tweetDetailUsername.text = "@${tweet.users.username}"
            tweetDetailPublicationDate.text = formatDate(tweet.createdAt)
            tweetDetailContent.text = tweet.content
            tweetDetailContentComment.hint = ("Poster votre tweet")
            tweetDetailRv.layoutManager =
                LinearLayoutManager(this@TweetDetailActivity, LinearLayoutManager.VERTICAL, false)
            tweetDetailRv.adapter =
                TweetsCommentRvAdapter(
                    this@TweetDetailActivity,
                    tweet.tweetComments,
                    onLikeClick = { tweetId ->
                        likeTweet(tweetId)
                    },
                    onUnlikeClick = { tweetId, likeId ->
                        unlikeTweet(tweetId, likeId)
                    },
                    onTweetClick = { tweetId ->
                        navigateToTweetDetail(tweetId)
                    })
        }
    }

    private fun likeTweet(tweetId: String) {
        tweetViewModel.likeTweet(tweetId) {
            fetchData()
        }
    }

    private fun unlikeTweet(tweetId: String, likeId: String) {
        val unlikeRequest = UnlikeRequest(tweetId, likeId)
        tweetViewModel.unlikeTweet(unlikeRequest) {
            fetchData()
        }
    }

    private fun postComment(content: String, parentTweetId: String) {
        val data = CommentRequest(content, parentTweetId)
        tweetViewModel.commentTweet(data) {
            fetchData()
        }
    }

    private fun navigateToUserDetail(userId: String) {
        val intent = Intent(this, UserDetailActivity::class.java).apply {
            putExtra(USER_ID_EXTRA, userId)
        }
        startActivity(intent)
    }

    private fun navigateToTweetDetail(tweetId: String) {
        val intent = Intent(this, TweetDetailActivity::class.java).apply {
            putExtra(TWEET_ID_EXTRA, tweetId)
        }
        startActivity(intent)
    }

    private fun fetchData() {
        intent.getStringExtra(TWEET_ID_EXTRA)?.let {
            tweetViewModel.getTweetById(it, object : GenericCallback<Tweet> {
                override fun onSuccess(data: Tweet) {
                    displayTweetDetails(data)
                }

                override fun onError(error: String) {
                }
            })
        }
    }

    private fun getToken(): String? {
        val sharedPreferences =
            this.getSharedPreferences("MY_APP_SHARED_PREFS", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("token", null)
        return token
    }
}