package com.example.movieplace.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieplace.MovieRepository
import com.example.movieplace.data.Result
import com.example.movieplace.data.model.*

class FavDirActCompViewModel : ViewModel() {
    private var repository: MovieRepository = MovieRepository()
    private var director1SetSuccessfully = MutableLiveData<Result<String>>()
    private var director2SetSuccessfully = MutableLiveData<Result<String>>()
    private var director1DeleteSuccessfully = MutableLiveData<Result<String>>()
    private var director2DeleteSuccessfully = MutableLiveData<Result<String>>()
    private var actor1SetSuccessfully = MutableLiveData<Result<String>>()
    private var actor2SetSuccessfully = MutableLiveData<Result<String>>()
    private var actor1DeleteSuccessfully = MutableLiveData<Result<String>>()
    private var actor2DeleteSuccessfully = MutableLiveData<Result<String>>()
    private var composer1SetSuccessfully = MutableLiveData<Result<String>>()
    private var composer2SetSuccessfully = MutableLiveData<Result<String>>()
    private var composer1DeleteSuccessfully = MutableLiveData<Result<String>>()
    private var composer2DeleteSuccessfully = MutableLiveData<Result<String>>()

    /**
     * gets the movies from the repository
     */
    fun getInfoUser(user: String): MutableLiveData<Result<User>> {
        return repository.getInfoUser(user)
    }

    /**
     * sets the movies from the repository
     */

    fun setDirector1(user: NewDirector) {
        director1SetSuccessfully = repository.setDirector1(user)
    }

    fun setDirector2(user: NewDirector) {
        director2SetSuccessfully = repository.setDirector2(user)
    }

    fun deleteDirector1(username: Delete) {
        director1DeleteSuccessfully = repository.deleteDirector1(username)
    }

    fun deleteDirector2(username: Delete) {
        director2DeleteSuccessfully = repository.deleteDirector2(username)
    }

    fun setActor1(user: NewActor) {
        actor1SetSuccessfully = repository.setActor1(user)
    }

    fun setActor2(user: NewActor) {
        actor2SetSuccessfully = repository.setActor2(user)
    }

    fun deleteActor1(username: Delete) {
        actor1DeleteSuccessfully = repository.deleteActor1(username)
    }

    fun deleteActor2(username: Delete) {
        actor2DeleteSuccessfully = repository.deleteActor2(username)
    }

    fun setCompositor1(user: NewCompositor) {
        composer1SetSuccessfully = repository.setCompositor1(user)
    }

    fun setCompositor2(user: NewCompositor) {
        composer2SetSuccessfully = repository.setCompositor2(user)
    }

    fun deleteComposer1(username: Delete) {
        composer1DeleteSuccessfully = repository.deleteComposer1(username)
    }

    fun deleteComposer2(username: Delete) {
        composer2DeleteSuccessfully = repository.deleteComposer2(username)
    }
}