package com.example.bai6.data.model

import com.google.gson.annotations.SerializedName

data class UserResult(
    @SerializedName("results")
    val users: List<User>,
    @SerializedName("info")
    val info: Infor
) {
    data class Infor(
        val seed: String,
        val results: Int,
        val page: Int,
        val version: Float
    )
}