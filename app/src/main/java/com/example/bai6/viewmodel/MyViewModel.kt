package com.example.bai6.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.bai6.data.UserApi
import com.example.bai6.data.model.User
import com.example.bai6.data.model.UserResult
import com.example.bai6.util.TAG
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel : ViewModel() {
    fun getAll(): LiveData<List<User>> {
        val users = MutableLiveData<List<User>>()
        viewModelScope.launch {
            UserApi.myApiService.getAll(20).enqueue(object : Callback<UserResult> {
                override fun onResponse(
                    call: Call<UserResult>,
                    response: Response<UserResult>
                ) {
                    if (response.isSuccessful) {
                        try {
                            users.value = response.body()?.users
                            Log.d(TAG, "onResponse: " + response.body()?.users?.joinToString())
                        } catch (e: Exception) {
                            Log.d(TAG, "onResponse: " + e.message)
                        }
                    } else {
                        Log.d(TAG, "onResponse Failure: " + response.message())
                    }
                }

                override fun onFailure(call: Call<UserResult>, t: Throwable) {
                    Log.d(TAG, "onFailure: " + t.message)
                }
            })
        }
        return users
    }
}

class MyViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MyViewModel() as T
        }
        return super.create(modelClass)
    }
}