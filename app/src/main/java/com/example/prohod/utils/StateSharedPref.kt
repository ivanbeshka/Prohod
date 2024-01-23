package com.example.prohod.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class StateSharedPref @Inject constructor(@ApplicationContext context: Context) {

    private val preferences = context.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)

    var skipStartScreen: Boolean
        get() {
            return preferences.getBoolean(SKIP_START_SCREEN_KEY, false)
        }
        set(value) {
            preferences.edit()
                .putBoolean(SKIP_START_SCREEN_KEY, value)
                .apply()
        }

    var skipRequestScreen: Boolean
        get() {
            return preferences.getBoolean(SKIP_REQUEST_SCREEN_KEY, false)
        }
        set(value) {
            preferences.edit()
                .putBoolean(SKIP_REQUEST_SCREEN_KEY, value)
                .apply()
        }

    private companion object {
        private const val SHARED_PREF_KEY = "state_shared_pref"
        private const val SKIP_START_SCREEN_KEY = "skip_start_screen"
        private const val SKIP_REQUEST_SCREEN_KEY = "skip_request_screen"
    }
}