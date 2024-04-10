package com.example.bai6.data

import com.example.bai6.data.model.UserResult
import com.example.bai6.util.BASE_URL
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface UserApiService {
    @GET("/api")
    fun getAll(@Query("results") results: Int): Call<UserResult>
}

object UserApi {
    val myApiService: UserApiService by lazy {
        retrofit.create(UserApiService::class.java)
    }
}