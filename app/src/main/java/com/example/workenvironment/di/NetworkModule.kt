package com.example.workenvironment.di

import android.util.Log
import com.example.workenvironment.data.remote.NetworkRquests
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

object NetworkModule {
    val apiServiceHeader = Interceptor { chain ->
        val request = chain.request().newBuilder().addHeader("Authorization", "Bearer " )
        Log.d("islam", "Interceptor: ${chain.request()} ")
        chain.proceed(request.build())
    }
    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(5, TimeUnit.SECONDS)
            .callTimeout(5, TimeUnit.SECONDS)
            .connectTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(apiServiceHeader)
            .build()
    }
    @Singleton
    @Provides
    @Named("loggingInterceptor")
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.HEADERS
            Log.d("islam", "provideLoggingInterceptor: ${this.level} ")
        }
    }
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
    @Provides
    fun provideApiClient(retrofit: Retrofit): NetworkRquests {
        return retrofit.create(NetworkRquests::class.java)
    }
}