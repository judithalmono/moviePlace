package com.example.movieplace.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieplace.MovieRepository
import com.example.movieplace.data.Result
import com.example.movieplace.data.model.ProfilePhoto

class SuggestionsViewModel : ViewModel() {
    private var repository: MovieRepository = MovieRepository()
    private var commentSetSuccessfully = MutableLiveData<Result<String>>()

    /**
     * sets from the repository
     */
    fun setProfilePhoto(user: ProfilePhoto) {
        commentSetSuccessfully = repository.setProfilePhoto(user)
    }
}