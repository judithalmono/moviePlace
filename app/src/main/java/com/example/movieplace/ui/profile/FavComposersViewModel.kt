package com.example.movieplace.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FavComposersViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Fav Composer Fragment"
    }
    val text: LiveData<String> = _text
}