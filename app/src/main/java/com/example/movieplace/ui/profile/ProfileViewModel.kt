package com.example.movieplace.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieplace.MovieRepository
import com.example.movieplace.data.Result
import com.example.movieplace.data.model.Delete
import com.example.movieplace.data.model.ProfilePhoto
import com.example.movieplace.data.model.User

class ProfileViewModel : ViewModel() {
    private var repository: MovieRepository = MovieRepository()
    private var photoSetSuccessfully = MutableLiveData<Result<String>>()
    private var photoDeleteSuccessfully = MutableLiveData<Result<String>>()

    /**
     * gets the movies from the repository
     */
    fun getInfoUser(user: String): MutableLiveData<Result<User>> {
        return repository.getInfoUser(user)
    }

    fun setProfilePhoto(user: ProfilePhoto) {
        photoSetSuccessfully = repository.setProfilePhoto(user)
    }

    fun deleteProfilePhoto(username: Delete) {
        photoDeleteSuccessfully = repository.deleteProfilePhoto(username)
    }

}