package com.example.bai6.data.model

import com.google.gson.annotations.SerializedName

data class ApiUser(
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

    data class User(
        @SerializedName("id")
        val id: Id,
        @SerializedName("name")
        val name: Name,
        @SerializedName("gender")
        val gender: String,
        @SerializedName("location")
        val location: Location,
        @SerializedName("email")
        val email: String,
        @SerializedName("phone")
        val phone: String,
        @SerializedName("cell")
        val cell: String,
        @SerializedName("picture")
        val picture: Picture,
        @SerializedName("mat")
        val nat: String,
        @SerializedName("login")
        val login: Login,
        @SerializedName("dob")
        val dob: Date,
        @SerializedName("registered")
        val registered: Date
    ) {
        data class Id(
            @SerializedName("name")
            val name: String,
            @SerializedName("value")
            val value: String
        ) {
            override fun toString(): String {
                return String.format(
                    "%s - %s",
                    name,
                    value
                )
            }
        }

        data class Name(
            @SerializedName("title")
            val title: String,
            @SerializedName("first")
            val first: String,
            @SerializedName("last")
            val last: String
        ) {
            override fun toString(): String {
                return String.format(
                    "%s. %s %s",
                    title,
                    first,
                    last
                )
            }
        }

        data class Location(
            @SerializedName("street")
            val street: Street,
            @SerializedName("city")
            val city: String,
            @SerializedName("state")
            val state: String,
            @SerializedName("country")
            val country: String,
            @SerializedName("postcode")
            val postcode: String,
            @SerializedName("coordinates")
            val coordinates: Coordinates,
            @SerializedName("timezone")
            val timezone: TimeZone
        ) {
            data class Street(
                @SerializedName("number")
                val number: Long,
                @SerializedName("name")
                val name: String
            ) {
                override fun toString(): String {
                    return String.format(
                        "%d, %s",
                        number,
                        name
                    )
                }
            }

            data class Coordinates(
                @SerializedName("latitude")
                val latitude: String,
                @SerializedName("longitude")
                val longitude: String
            ) {
                override fun toString(): String {
                    return String.format("%s, %s", latitude, longitude)
                }
            }

            data class TimeZone(
                @SerializedName("offset")
                val offset: String,
                @SerializedName("description")
                val description: String
            ) {
                override fun toString(): String {
                    return String.format("%s, %s", offset, description)
                }
            }

            override fun toString(): String {
                return String.format(
                    "%s, %s, %s, %s",
                    street.toString(),
                    state,
                    city,
                    country
                )
            }
        }

        data class Picture(
            @SerializedName("large")
            val large: String,
            @SerializedName("medium")
            val medium: String,
            @SerializedName("thumbnail")
            val thumbnail: String
        ) {
            override fun toString(): String {
                return String.format(
                    "Large: %s\nMedium: %s\nThumbnail: %s",
                    large,
                    medium,
                    thumbnail
                )
            }
        }

        data class Login(
            @SerializedName("uuid")
            val uuid: String,
            @SerializedName("username")
            val username: String,
            @SerializedName("password")
            val password: String,
            @SerializedName("salt")
            val salt: String,
            @SerializedName("md5")
            val md5: String,
            @SerializedName("sha1")
            val sha1: String,
            @SerializedName("sha256")
            val sha256: String
        ) {
            override fun toString(): String {
                return String.format(
                    "Uuid: %s\nUsername: %s",
                    uuid,
                    username
                )
            }
        }

        data class Date(
            @SerializedName("date")
            val date: String,
            @SerializedName("age")
            val age: Int
        ) {
            override fun toString(): String {
                return String.format(
                    "Date: %s\nAge: %d",
                    date,
                    age
                )
            }
        }
    }
}