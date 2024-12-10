package com.example.twitter_like.home_page
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.twitter_like.CreateAccountActivity
import com.example.twitter_like.LoginActivity
import com.example.twitter_like.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        val createAccountButton = findViewById<Button>(R.id.button_create_account)
        val loginButton = findViewById<Button>(R.id.button_login)

         createAccountButton.setOnClickListener {
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
        }

         loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}