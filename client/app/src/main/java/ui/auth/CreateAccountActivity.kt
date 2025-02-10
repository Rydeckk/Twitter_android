package ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import viewmodel.landing.LandingPageActivity
import com.example.twitter_like.R
import com.example.twitter_like.viewmodel.CreateAccountViewModel



class CreateAccountActivity : AppCompatActivity() {

    // Initialisation du ViewModel
    private val viewModel: CreateAccountViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)
        observeViewModel()
        val crossButton = findViewById<ImageView>(R.id.cross)
        val usernameField = findViewById<EditText>(R.id.username)
        val emailField = findViewById<EditText>(R.id.email)
        val passwordField = findViewById<EditText>(R.id.password)
        val nextButton = findViewById<Button>(R.id.next_button)

        crossButton.setOnClickListener {
            finish()
        }

        // Observer les résultats du ViewModel
        observeViewModel()

        nextButton.setOnClickListener {
            val username = usernameField.text.toString().trim()
            val email = emailField.text.toString().trim()
            val password = passwordField.text.toString().trim()

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Tous les champs doivent être remplis", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Email invalide", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Appel au ViewModel pour enregistrer l'utilisateur
            viewModel.registerUser(username, email, password)
        }
    }

    private fun observeViewModel() {
        viewModel.registerSuccess.observe(this) { success ->
            if (success) {
                Toast.makeText(this, "Inscription réussie", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LandingPageActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        viewModel.errorMessage.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }
    }
}
