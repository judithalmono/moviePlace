package com.example.movieplace.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChangePasswordViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Change Password Fragment"
    }
    val text: LiveData<String> = _text
}