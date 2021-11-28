package com.example.movieplace.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FavActorsViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Fav Actors Fragment"
    }
    val text: LiveData<String> = _text
}