package com.example.twitter_like.views.pager_fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.twitter_like.R
import com.example.twitter_like.data.model.auth.Login
import com.example.twitter_like.data.model.auth.Register
import com.example.twitter_like.data.request.auth.LoginRequest
import com.example.twitter_like.network.callback.GenericCallback
import com.example.twitter_like.pages.interfaces.AuthHandler
import com.example.twitter_like.repositories.AuthRepository
import com.example.twitter_like.viewmodel.AuthViewModel
import com.example.twitter_like.viewmodel.factories.AuthViewModelFactory

class LoginFragment : Fragment() {
    private lateinit var _authHandler: AuthHandler
    private lateinit var _mainPager: ViewPager2

    private val authViewModel: AuthViewModel by viewModels {
        AuthViewModelFactory(AuthRepository())
    }

    companion object {
        fun newInstance(authHandler: AuthHandler, mainPager: ViewPager2): LoginFragment {
            return LoginFragment().also {
                it._authHandler = authHandler
                it._mainPager = mainPager
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val emailInput = view.findViewById<EditText>(R.id.email)
        val passwordInput = view.findViewById<EditText>(R.id.password)
        val goToRegister = view.findViewById<Button>(R.id.go_to_register)
        val onLogin = view.findViewById<Button>(R.id.login_button)

        onLogin.setOnClickListener {
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
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

            authViewModel.login(LoginRequest(email, password), object : GenericCallback<Login> {
                override fun onSuccess(data: Login) {
                    Toast.makeText(requireContext(), "Connexion réussie !", Toast.LENGTH_SHORT)
                        .show()
                    val sharedPreferences = requireContext().getSharedPreferences(
                        "MY_APP_SHARED_PREFS",
                        Context.MODE_PRIVATE
                    )
                    sharedPreferences.edit()
                        .putString("token", data.accessToken)
                        .apply()

                    val token = sharedPreferences.getString("token", null)
                    displayProtectedFragment()
                }

                override fun onError(error: String) {
                    Toast.makeText(
                        requireContext(),
                        "Email ou mot de passe non valide",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }

        goToRegister.setOnClickListener {
            _authHandler.displayRegisterPage()
        }
    }

    private fun displayProtectedFragment() {
        this._mainPager.currentItem = 0
    }

}
