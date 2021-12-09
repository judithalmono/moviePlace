package com.example.movieplace.data.model

data class User(
    val _id: String,
    val actors_pref: List<Int>,
    val compositors_pref: String,
    val creation_date: String,
    val date_birth: String,
    val directors_pref: String,
    val email: String,
    val full_name: String,
    val img: String,
    val password: String,
    val personal_address: String,
    val phone: String,
    val sex: String,
    val user_genres: List<String>,
    val username: String,
)