package com.example.disastermanagement.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesHelper(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "disaster_management_prefs"
        private const val KEY_USER_NAME = "user_name"
        private const val KEY_USER_ROLE = "user_role"
        private const val KEY_INSTITUTION = "institution"
        private const val KEY_REGION = "region"
        private const val KEY_NOTIFICATIONS_ENABLED = "notifications_enabled"
        private const val KEY_EMERGENCY_CONTACTS_SETUP = "emergency_contacts_setup"
    }

    fun saveUserName(name: String) {
        prefs.edit().putString(KEY_USER_NAME, name).apply()
    }

    fun getUserName(): String? {
        return prefs.getString(KEY_USER_NAME, null)
    }

    fun saveUserRole(role: String) {
        prefs.edit().putString(KEY_USER_ROLE, role).apply()
    }

    fun getUserRole(): String? {
        return prefs.getString(KEY_USER_ROLE, null)
    }

    fun saveInstitution(institution: String) {
        prefs.edit().putString(KEY_INSTITUTION, institution).apply()
    }

    fun getInstitution(): String? {
        return prefs.getString(KEY_INSTITUTION, null)
    }

    fun saveRegion(region: String) {
        prefs.edit().putString(KEY_REGION, region).apply()
    }

    fun getRegion(): String? {
        return prefs.getString(KEY_REGION, null)
    }

    fun setNotificationsEnabled(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_NOTIFICATIONS_ENABLED, enabled).apply()
    }

    fun areNotificationsEnabled(): Boolean {
        return prefs.getBoolean(KEY_NOTIFICATIONS_ENABLED, true)
    }

    fun setEmergencyContactsSetup(setup: Boolean) {
        prefs.edit().putBoolean(KEY_EMERGENCY_CONTACTS_SETUP, setup).apply()
    }

    fun isEmergencyContactsSetup(): Boolean {
        return prefs.getBoolean(KEY_EMERGENCY_CONTACTS_SETUP, false)
    }
}

