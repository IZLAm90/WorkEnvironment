package com.example.workenvironment.datastore

import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceKeys {
    val UserName= stringPreferencesKey("name")
    val UserPassWord= stringPreferencesKey("password")
}