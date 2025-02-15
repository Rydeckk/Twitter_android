package com.example.twitter_like.views.pager_fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.twitter_like.R

import com.example.twitter_like.pages.interfaces.ProtectedPageHandler
import com.example.twitter_like.views.ProtectedPagerAdapter
import com.google.android.material.navigation.NavigationView

class ProtectedPageFragment : Fragment(), ProtectedPageHandler {
    private lateinit var dymagramPager: ViewPager2
    lateinit var _mainPager: ViewPager2
    private lateinit var drawerLayout: DrawerLayout

    companion object {
        fun newInstance(mainPager: ViewPager2): ProtectedPageFragment {
            return ProtectedPageFragment().also {
                it._mainPager = mainPager
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.protected_page_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        val navigationView: NavigationView = view.findViewById(R.id.nav_view)
        drawerLayout = view.findViewById(R.id.drawer_layout)
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            requireActivity(),
            drawerLayout,
            toolbar,
            R.string.nav_open,
            R.string.nav_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_profile -> {
                    displayUserProfilePage()
                }

                R.id.nav_home -> {
                    displayHomePage()
                }

                R.id.nav_explore -> {
                    displaySearchPage()
                }

                R.id.nav_notification -> {
                    displayNotificationPage()
                }

                R.id.nav_message -> {
                    displayMessagesPage()
                }

                R.id.nav_deconnexion -> {
                    logout()
                }
            }
            drawerLayout.closeDrawers()
            true
        }

        view.findViewById<ImageButton>(R.id.bt_home).setOnClickListener {
            displayHomePage()
        }

        view.findViewById<ImageButton>(R.id.bt_search).setOnClickListener {
            displaySearchPage()
        }

        view.findViewById<ImageButton>(R.id.bt_notification).setOnClickListener {
            displayNotificationPage()
        }

        view.findViewById<ImageButton>(R.id.bt_message).setOnClickListener {
            displayMessagesPage()
        }

        setUpMainPager(view)
    }

    private fun setUpMainPager(view: View) {
        dymagramPager = view.findViewById(R.id.protected_page_pager)
        val protectedPagerAdapter = ProtectedPagerAdapter(this, this, this._mainPager)
        dymagramPager.adapter = protectedPagerAdapter
        this.dymagramPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateButtonColors(position, view)
            }
        })
        displayHomePage()
    }

    private fun updateButtonColors(activePage: Int, view: View) {
        val buttons = listOf(
            view.findViewById<ImageButton>(R.id.bt_home),
            view.findViewById<ImageButton>(R.id.bt_search),
            view.findViewById<ImageButton>(R.id.bt_notification),
            view.findViewById<ImageButton>(R.id.bt_message)
        )
        val activeColor = ContextCompat.getColor(requireContext(), R.color.active_color)
        val inactiveColor = ContextCompat.getColor(requireContext(), R.color.inactive_color)
        buttons.forEachIndexed { index, button ->
            button.setColorFilter(if (index == activePage) activeColor else inactiveColor)
        }
    }

    override fun displayHomePage() {
        this.dymagramPager.currentItem = 0
    }

    override fun displaySearchPage() {
        this.dymagramPager.currentItem = 1
    }

    override fun displayNotificationPage() {
        this.dymagramPager.currentItem = 2
    }

    override fun displayMessagesPage() {
        this.dymagramPager.currentItem = 3
    }

    override fun displayUserProfilePage() {
        this.dymagramPager.currentItem = 4
    }

    private fun logout() {
        val sharedPreferences =
            requireContext().getSharedPreferences("MY_APP_SHARED_PREFS", Context.MODE_PRIVATE)
        sharedPreferences.edit().remove("token").apply()
        _mainPager.currentItem = 1
    }

}