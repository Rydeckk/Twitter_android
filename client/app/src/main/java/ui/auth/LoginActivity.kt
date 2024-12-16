package ui.auth

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.twitter_like.R

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
