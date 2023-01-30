package com.example.workenvironment.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.example.workenvironment.data.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class ReposUserData(private val prefsDataStore: DataStore<androidx.datastore.preferences.core.Preferences>) :StoreUserData {
    override suspend fun setUserData(data: UserData) {
        prefsDataStore.edit{ pref->
        pref[PreferenceKeys.UserName]=data.userName
        pref[PreferenceKeys.UserPassWord]=data.passWord
        }
    }

    override suspend fun getUserData(): Flow<UserData> {
       return prefsDataStore.data.catch { ex->
            if (ex is IOException) {
                emit(emptyPreferences())
            } else {
                throw ex
            }

       }.map { preference->
            UserData(preference[PreferenceKeys.UserName]?:""
               ,preference[PreferenceKeys.UserPassWord]?: "")
       }
    }
}