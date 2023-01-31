package com.example.workenvironment.datastore


import com.example.workenvironment.model.UserData
import kotlinx.coroutines.flow.Flow

interface StoreUserData {
    suspend fun setUserData(data : UserData)
    suspend fun getUserData() : Flow<UserData>
}