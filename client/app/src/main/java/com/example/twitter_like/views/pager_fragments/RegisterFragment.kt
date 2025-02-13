package com.example.twitter_like.views.pager_fragments

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.twitter_like.R
import com.example.twitter_like.data.model.auth.Register
import com.example.twitter_like.data.request.auth.RegisterRequest
import com.example.twitter_like.network.callback.GenericCallback
import com.example.twitter_like.pages.interfaces.AuthHandler
import com.example.twitter_like.repositories.AuthRepository
import com.example.twitter_like.viewmodel.AuthViewModel
import com.example.twitter_like.viewmodel.factories.AuthViewModelFactory

class RegisterFragment : Fragment() {
    private lateinit var _authHandler: AuthHandler

    private val authViewModel: AuthViewModel by viewModels {
        AuthViewModelFactory(AuthRepository())
    }

    companion object {
        fun newInstance(authHandler: AuthHandler): RegisterFragment {
            return RegisterFragment().also {
                it._authHandler = authHandler
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val usernameInput = view.findViewById<EditText>(R.id.username)
        val emailInput = view.findViewById<EditText>(R.id.email)
        val passwordInput = view.findViewById<EditText>(R.id.password)
        val goToLogin = view.findViewById<Button>(R.id.go_to_login)
        val onRegister = view.findViewById<Button>(R.id.register_button)

        onRegister.setOnClickListener {
            val username = usernameInput.text.toString().trim()
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Tous les champs doivent être remplis",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(requireContext(), "Email invalide", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val registerData = RegisterRequest(username, email, password)

            authViewModel.register(registerData, object : GenericCallback<Register> {
                override fun onSuccess(data: Register) {
                    Toast.makeText(requireContext(), "Inscription réussie !", Toast.LENGTH_SHORT)
                        .show()
                    _authHandler.displayLoginPage()
                }
                override fun onError(error: String) {
                    Toast.makeText(
                        requireContext(),
                        "Authentification non valide",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }

        goToLogin.setOnClickListener {
            _authHandler.displayLoginPage()
        }
    }

}