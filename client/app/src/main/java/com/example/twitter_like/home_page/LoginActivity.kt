package com.example.twitter_like

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val crossButton = findViewById<ImageView>(R.id.cross)

        crossButton.setOnClickListener {
            finish()
        }
    }
}
