package com.example.cyberescape

import android.content.Context
import android.content.SharedPreferences

/**
 * GamePreferences manages persistent level progression using SharedPreferences and Boolean flags.
 * Level 1 is always unlocked.
 * Level 2 unlocks after successfully completing Level 1.
 * Level 3 unlocks after successfully completing Level 2.
 */
object GamePreferences {

    private const val PREF_NAME = "cyber_escape_prefs"
    private const val KEY_LEVEL_2_UNLOCKED = "isLevel2Unlocked"
    private const val KEY_LEVEL_3_UNLOCKED = "isLevel3Unlocked"

    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun isLevel1Unlocked(): Boolean {
        return true // Level 1 is unlocked by default
    }

    fun isLevel2Unlocked(context: Context): Boolean {
        return getPrefs(context).getBoolean(KEY_LEVEL_2_UNLOCKED, false)
    }

    fun setLevel2Unlocked(context: Context, unlocked: Boolean) {
        getPrefs(context).edit().putBoolean(KEY_LEVEL_2_UNLOCKED, unlocked).apply()
    }

    fun isLevel3Unlocked(context: Context): Boolean {
        return getPrefs(context).getBoolean(KEY_LEVEL_3_UNLOCKED, false)
    }

    fun setLevel3Unlocked(context: Context, unlocked: Boolean) {
        getPrefs(context).edit().putBoolean(KEY_LEVEL_3_UNLOCKED, unlocked).apply()
    }

    fun resetProgress(context: Context) {
        getPrefs(context).edit().clear().apply()
    }
}
