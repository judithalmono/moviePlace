package com.example.movieplace.data.model

data class Scene(
    val Actors: List<String>,
    val Description: String,
    val Name: String,
    val Songs: List<Any>,
    val _id: String,
    val id: Int,
    val img: String,
    val location: List<Double>,
    val nameLoc: String
)