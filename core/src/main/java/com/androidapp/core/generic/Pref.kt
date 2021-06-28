package com.androidapp.core.generic

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

/**
 *  Created by Iván Bolaños on 02/06/2021
 */



object Pref {
    private const val PREF_SHARED_NAME = "pref.gou.pilot"
    private lateinit var preferences: SharedPreferences

    private const val KEY_LOGIN = "key.login"


    fun init(application: Application) {
        preferences = application.getSharedPreferences(PREF_SHARED_NAME, Context.MODE_PRIVATE)
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return preferences.getBoolean(key, defaultValue)
    }

    fun setBoolean(key: String, value: Boolean) {
        preferences.edit().putBoolean(key, value).apply()
    }

    fun getInt(key: String, defaultValue: Int): Int {
        return preferences.getInt(key, defaultValue)
    }

    fun setInt(key: String, value: Int) {
        preferences.edit().putInt(key, value).apply()
    }

    fun getLong(key: String, defaultValue: Long): Long {
        return preferences.getLong(key, defaultValue)
    }

    fun setLong(key: String, value: Long) {
        preferences.edit().putLong(key, value).apply()
    }

    fun getString(key: String, defaultValue: String): String {
        return preferences.getString(key, defaultValue) ?: ""
    }

    fun setString(key: String, value: String) {
        preferences.edit().putString(key, value).apply()
    }

    fun setFloat(key: String, value: Float) {
        preferences.edit().putFloat(key, value).apply()
    }

    fun getFloat(key: String, defaultValue: Float): Float {
        return preferences.getFloat(key, defaultValue)
    }

    fun setSet(key: String, value: Set<String>) {
        preferences.edit().putStringSet(key, value).apply()
    }

    fun getSet(key: String, defaultValue: Set<String>): Set<String>? {
        return preferences.getStringSet(key, defaultValue)
    }

    fun contains(key: String): Boolean {
        return preferences.contains(key)
    }

    fun clear() {
        preferences.edit().clear().apply()
    }

    fun remove(key: String) {
        val editor = preferences.edit()
        editor.remove(key)
        editor.apply()
    }


    // Login
    fun setLogIn(flag: Boolean) = setBoolean(KEY_LOGIN, flag)

    fun getLogIn(): Boolean = getBoolean(KEY_LOGIN, false)

    fun deleteLogIn() {
        remove(KEY_LOGIN)
    }

}