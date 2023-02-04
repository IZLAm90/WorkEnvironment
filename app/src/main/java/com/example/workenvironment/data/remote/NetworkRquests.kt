package com.example.workenvironment.data.remote

import com.example.workenvironment.model.UserData
import com.example.workenvironment.model.responce.LoginResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface NetworkRquests {


    @POST("api/v1/Auth/PortalLogin")
    suspend fun login(@Body loginUser: UserData): LoginResponse

}