package com.example.twitter_like.ui.token

import android.content.Context
import android.content.SharedPreferences

class TokenManager(context: Context) {
    companion object {
        private const val PREFS_NAME = "auth_prefs"
        private const val TOKEN_KEY = "access_token"
    }

    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    // Fonction pour sauvegarder le token
    fun saveToken(token: String) {
        prefs.edit().putString(TOKEN_KEY, token).apply()
    }

    // Fonction pour récupérer le token
    fun getToken(): String? {
        return prefs.getString(TOKEN_KEY, null)
    }

    // Fonction pour supprimer le token (ex: lors de la déconnexion)
    fun clearToken() {
        prefs.edit().remove(TOKEN_KEY).apply()
    }
}
