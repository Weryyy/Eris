package com.weryyy.eris.data

import android.content.Context
import android.content.SharedPreferences

class AuthManager(context: Context) {
    private val prefs: SharedPreferences = 
        context.getSharedPreferences("ErisPrefs", Context.MODE_PRIVATE)
    
    fun saveAccessToken(token: String) {
        prefs.edit().putString("access_token", token).apply()
    }
    
    fun getAccessToken(): String? {
        return prefs.getString("access_token", null)
    }
    
    fun clearAccessToken() {
        prefs.edit().remove("access_token").apply()
    }
    
    fun isLoggedIn(): Boolean {
        return getAccessToken() != null
    }
}
