package com.example.movieplace.data.model

data class User(
    val _id: String,
    val actors_pref: List<String>,
    val compositors_pref: String,
    val creation_date: String,
    val date_birth: String,
    val directors_pref: String,
    val email: String,
    val full_name: String,
    val password: String,
    val personal_address: String,
    val phone: Int,
    val sex: String,
    val user_genres: List<String>,
    val username: String
)