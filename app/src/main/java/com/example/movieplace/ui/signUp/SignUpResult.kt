package com.example.movieplace.ui.signUp

data class SignUpResult(
    val success: Boolean? = null, // bool innecessari però esque anira checkejant des de la Activity si és null.
    val error: Int? = null
)