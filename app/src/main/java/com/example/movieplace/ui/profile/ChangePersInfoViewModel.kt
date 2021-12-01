package com.example.movieplace.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieplace.MovieRepository
import com.example.movieplace.data.Result
import com.example.movieplace.data.model.*

class ChangePersInfoViewModel : ViewModel() {
    private var repository: MovieRepository = MovieRepository()
    var usernameSetSuccessfully = MutableLiveData<Result<String>>()
    var fullNameSetSuccessfully = MutableLiveData<Result<String>>()
    var emailSetSuccessfully = MutableLiveData<Result<String>>()
    var telefSetSuccessfully = MutableLiveData<Result<String>>()
    var birthSetSuccessfully = MutableLiveData<Result<String>>()
    var addressSetSuccessfully = MutableLiveData<Result<String>>()
    var sexSetSuccessfully = MutableLiveData<Result<String>>()

    /**
     * gets the movies from the repository
     */
    fun getInfoUser(user: String): MutableLiveData<Result<User>> {
        return repository.getInfoUser(user)
    }

    /**
     * sets the movies from the repository
     */
    fun setUsername(user: Username) {
        usernameSetSuccessfully = repository.setUsername(user)
    }

    fun setFullName(user: FullName) {
        fullNameSetSuccessfully = repository.setFullName(user)
    }

    fun setEmail(user: Email) {
        emailSetSuccessfully = repository.setEmail(user)
    }

    fun setTelefon(user: Phone) {
        telefSetSuccessfully = repository.setTelefon(user)
    }

    fun setBirth(user: Birth) {
        birthSetSuccessfully = repository.setBirth(user)
    }

    fun setAddress(user: Address) {
        addressSetSuccessfully = repository.setAddress(user)
    }

    fun setSex(user: Sex) {
        sexSetSuccessfully = repository.setSex(user)
    }

}