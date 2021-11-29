package com.example.movieplace.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieplace.MovieRepository
import com.example.movieplace.data.Result
import com.example.movieplace.data.model.User

class ProfileViewModel : ViewModel() {
    private var repository: MovieRepository = MovieRepository()
    var usernameSetSuccessfully = MutableLiveData<Result<String>>()
    var fullNameSetSuccessfully = MutableLiveData<Result<String>>()
    var emailSetSuccessfully = MutableLiveData<Result<String>>()

    /**
     * gets the movies from the repository
     */
    fun getInfoUser(user: String): MutableLiveData<Result<User>> {
        return repository.getInfoUser(user)
    }

    /**
     * sets the movies from the repository
     */
    fun setUsername(usr: String, newus: String) {
        usernameSetSuccessfully = repository.setUsername(usr, newus)
    }

    fun setFullName(usr: String, newname: String) {
        fullNameSetSuccessfully = repository.setFullName(usr, newname)
    }

    fun setEmail(usr: String, newmail : String) {
        emailSetSuccessfully = repository.setEmail(usr, newmail)
    }

}