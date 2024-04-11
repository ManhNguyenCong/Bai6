package com.example.bai6.data

import com.example.bai6.data.model.ApiUser
import com.example.bai6.util.BASE_URL
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface UserApiService {
    @GET("/api")
    fun getAll(@Query("results") results: Int): Call<ApiUser>
}