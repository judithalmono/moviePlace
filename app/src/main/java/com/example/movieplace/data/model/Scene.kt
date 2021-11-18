package com.example.movieplace.data.model

data class Scene(
    val _id: String,
    val actors: List<String>,
    val description: String,
    val id: String,
    val location: String,
    val songs: List<Any>
)