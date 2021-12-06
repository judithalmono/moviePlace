package com.example.movieplace.ui.login

import androidx.lifecycle.LiveData
import com.example.movieplace.data.model.LoggedInUser

data class LoggedInUserView(
    val data: LiveData<LoggedInUser>
    // ... other data fields that may be accessible to the UI
)
