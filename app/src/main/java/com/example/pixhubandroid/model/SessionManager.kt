package com.example.pixhubandroid.model

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

object SessionManager {

    private const val PREF_NAME = "user_session"
    private const val KEY_ACCOUNT = "account"

    fun saveAccount(context: Context, account: AccountBean) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val accountJson = Gson().toJson(account)
        editor.putString(KEY_ACCOUNT, accountJson)
        editor.apply()
    }

    fun getAccount(context: Context): AccountBean? {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val accountJson = sharedPreferences.getString(KEY_ACCOUNT, null)
        return if (accountJson != null) {
            Gson().fromJson(accountJson, AccountBean::class.java)
        } else {
            null
        }
    }

    fun logout(context: Context) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.remove(KEY_ACCOUNT)
        editor.apply()
    }
}
