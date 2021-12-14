package com.example.movieplace.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieplace.MovieRepository
import com.example.movieplace.data.Result
import com.example.movieplace.data.model.Delete
import com.example.movieplace.data.model.NewGenre
import com.example.movieplace.data.model.User

class FavGenresViewModel : ViewModel() {
    private var repository: MovieRepository = MovieRepository()
    private var genres1SetSuccessfully = MutableLiveData<Result<String>>()
    private var genres2SetSuccessfully = MutableLiveData<Result<String>>()
    private var genres1DeleteSuccessfully = MutableLiveData<Result<String>>()
    private var genres2DeleteSuccessfully = MutableLiveData<Result<String>>()

    /**
     * gets the movies from the repository
     */
    fun getInfoUser(user: String): MutableLiveData<Result<User>> {
        return repository.getInfoUser(user)
    }

    /**
     * sets the movies from the repository
     */
    fun setGenre1(user: NewGenre) {
        genres1SetSuccessfully = repository.setGenre1(user)
    }

    fun setGenre2(user: NewGenre) {
        genres2SetSuccessfully = repository.setGenre2(user)
    }

    fun deleteGenre1(username: Delete) {
        genres1DeleteSuccessfully = repository.deleteGenre1(username)
    }

    fun deleteGenre2(username: Delete) {
        genres2DeleteSuccessfully = repository.deleteGenre2(username)
    }

}