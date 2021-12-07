package com.example.movieplace.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieplace.MovieRepository
import com.example.movieplace.data.Result
import com.example.movieplace.data.model.NewActor
import com.example.movieplace.data.model.User

class FavActorsViewModel : ViewModel() {
    private var repository: MovieRepository = MovieRepository()
    var actorSetSuccessfully = MutableLiveData<Result<String>>()

    /**
     * gets the movies from the repository
     */
    fun getInfoUser(user: String): MutableLiveData<Result<User>> {
        return repository.getInfoUser(user)
    }

    /**
     * sets the movies from the repository
     */
    fun setActor(user: NewActor) {
        actorSetSuccessfully = repository.setActor(user)
    }
}