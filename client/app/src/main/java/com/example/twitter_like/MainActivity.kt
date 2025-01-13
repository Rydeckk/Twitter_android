package com.example.twitter_like

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.twitter_like.viewmodel.MainViewModel
import com.example.twitter_like.views.fragment.ConversationFragment

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Initialisation du ViewModel
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // Observer le fragment courant à afficher
        viewModel.selectedFragment.observe(this, { fragment ->
            fragment?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, it)
                    .commit()
            }
        })

        // Si aucune sauvegarde (premier lancement), définir un fragment par défaut
        if (savedInstanceState == null) {
            viewModel.setFragment(ConversationFragment()) // Définir le fragment initial
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}