package com.example.workenvironment.repo

import com.example.workenvironment.data.remote.NetworkRquests
import com.example.workenvironment.model.UserData
import com.example.workenvironment.model.responce.LoginResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginRepo @Inject constructor(private val retrofitCall :NetworkRquests) {
    suspend fun login(userData: UserData) :LoginResponse = retrofitCall.login(userData)
}