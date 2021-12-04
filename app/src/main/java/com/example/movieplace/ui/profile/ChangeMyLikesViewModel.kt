package com.example.movieplace.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChangeMyLikesViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Change My Likes Fragment"
    }
    val text: LiveData<String> = _text
}