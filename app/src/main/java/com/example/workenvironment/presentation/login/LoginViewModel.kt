package com.example.workenvironment.presentation.login

import androidx.lifecycle.ViewModel
import com.example.workenvironment.model.UserData
import com.example.workenvironment.model.responce.LoginResponse
import com.example.workenvironment.repo.LoginRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject
@HiltViewModel
class LoginViewModel @Inject constructor(private val repo: LoginRepo) : ViewModel() {
    suspend fun userLogin(userData: UserData) : LoginResponse =repo.login(userData = userData)
}