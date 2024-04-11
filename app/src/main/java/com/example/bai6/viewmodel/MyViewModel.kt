package com.example.bai6.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bai6.data.UserApiService
import com.example.bai6.data.model.ApiUser
import com.example.bai6.util.TAG
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyViewModel @Inject constructor(private val userApiService: UserApiService) {
    fun getAll(): LiveData<List<ApiUser.User>> {
        val users = MutableLiveData<List<ApiUser.User>>()
        userApiService.getAll(20).enqueue(object : Callback<ApiUser> {
            override fun onResponse(
                call: Call<ApiUser>,
                response: Response<ApiUser>
            ) {
                if (response.isSuccessful) {
                    try {
                        users.value = response.body()?.users
                    } catch (e: Exception) {
                        Log.d(TAG, "onResponse: " + e.message)
                    }
                } else {
                    Log.d(TAG, "onResponse Failure: " + response.message())
                }
            }

            override fun onFailure(call: Call<ApiUser>, t: Throwable) {
                Log.d(TAG, "onFailure: " + t.message)
            }
        })
        return users
    }
}