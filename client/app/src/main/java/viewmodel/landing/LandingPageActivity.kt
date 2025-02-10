package viewmodel.landing

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.twitter_like.R
import com.example.twitter_like.ui.token.TokenManager

class LandingPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)

        // Initialiser le TokenManager
        val tokenManager = TokenManager(this)

        // Récupérer le token
        val token = tokenManager.getToken()

        // Afficher le token dans un Toast (option 1)
        Toast.makeText(this, "Token : $token", Toast.LENGTH_LONG).show()

        // Afficher le token dans les logs (option 2)
        println("Token récupéré : $token")

        // Afficher le token dans une TextView (option 3)
        val tokenTextView = findViewById<TextView>(R.id.token_text_view)
        tokenTextView.text = token ?: "Aucun token trouvé"
    }
}
