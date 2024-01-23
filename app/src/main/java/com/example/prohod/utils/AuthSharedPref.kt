package com.example.prohod.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AuthSharedPref @Inject constructor(@ApplicationContext context: Context) {

    private val preferences = context.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)

    var token: String?
        get() {
            return preferences.getString(TOKEN_KEY, null)
        }
        set(value) {
            value?.let {
                preferences.edit()
                    .putString(TOKEN_KEY, it)
                    .apply()
            }
        }

    var userId: String?
        get() {
            return preferences.getString(USER_ID_KEY, null)
        }
        set(value) {
            value?.let {
                preferences.edit()
                    .putString(USER_ID_KEY, it)
                    .apply()
            }
        }

    var login: String?
        get() {
            return preferences.getString(LOGIN_KEY, null)
        }
        set(value) {
            value?.let {
                preferences.edit()
                    .putString(LOGIN_KEY, it)
                    .apply()
            }
        }

    var password: String?
        get() {
            return preferences.getString(PASS_KEY, null)
        }
        set(value) {
            value?.let {
                preferences.edit()
                    .putString(PASS_KEY, it)
                    .apply()
            }
        }

    private companion object {
        private const val SHARED_PREF_KEY = "auth_shared_pref"
        private const val TOKEN_KEY = "token"
        private const val USER_ID_KEY = "user_id"
        private const val LOGIN_KEY = "login"
        private const val PASS_KEY = "pass"
    }
}