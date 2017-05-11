package com.tofi.peekazoo

import android.content.Context
import android.support.annotation.StringDef

private const val SHARED_PREFERENCES_NAME = "insert_app_name"

/**
 * Created by Derek on 06/05/2017.
 * Manages reads and writes to application shared preferences
 */
class SharedPreferencesManager(context : Context) {

    companion object {

        @StringDef(SESSION_ID)
        @Retention(AnnotationRetention.SOURCE)
        annotation class Key

        const val SESSION_ID = "session_id"
    }

    private val sharedPrefs = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun writeStringPreference(@Key key : String, value : String) {

        synchronized(this) {
            sharedPrefs
                    .edit()
                    .putString(key, value)
                    .apply()
        }
    }

    fun getStringPreference(@Key key : String, defaultValue : String) : String {

        synchronized(this) {
            return sharedPrefs.getString(key, defaultValue)
        }
    }
}
