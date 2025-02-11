package com.example.twitter_like.views.pager_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.twitter_like.R
import com.example.twitter_like.pages.interfaces.AuthHandler
import com.example.twitter_like.views.AuthAdapter
import androidx.fragment.app.Fragment

class AuthFragment : Fragment(), AuthHandler {
    private lateinit var dymagramPager: ViewPager2
    private lateinit var _authHandler: AuthHandler

    companion object {
        fun newInstance(authHandler: AuthHandler): AuthFragment {
            return AuthFragment().also {
                it._authHandler = authHandler
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.auth_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpMainPager(view)
    }

    private fun setUpMainPager(view: View) {
        dymagramPager = view.findViewById(R.id.auth_pager)
        val authPagerAdapter = AuthAdapter(this, this)
        dymagramPager.adapter = authPagerAdapter
        displayRegisterPage()
    }

    override fun displayRegisterPage() {
        dymagramPager.currentItem = 0
    }

    override fun displayLoginPage() {
        dymagramPager.currentItem = 1
    }
}
