package com.example.movieplace.ui.signUp

data class SignUpFormState(
    val emailError: Int? = null,
    val usernameError: Int? = null,
    val passwordError: Int? = null,
    val passwordError2: Int? = null,
    val isDataValid: Boolean? = null,
)