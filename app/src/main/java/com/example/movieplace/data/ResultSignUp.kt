package com.example.movieplace.data

data class ResultSignUp(
    val success: Boolean? = null, // bool innecessari però esque anira checkejant des de la Activity si és null.
    val error: Exception? = null
)