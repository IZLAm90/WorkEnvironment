package com.example.workenvironment.data.remote

import com.example.workenvironment.model.UserData
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkRquests {
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") api_key: String = "Constant.API_Key",
        @Query("page") page: Int = 1
    ): UserData
}