package com.example.movieplace.ui.movie2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieplace.MovieRepository
import com.example.movieplace.data.Result
import com.example.movieplace.data.model.Movie

class Movie2ViewModel : ViewModel() {

    private var repository: MovieRepository = MovieRepository()
    var movies : MutableLiveData<Result<List<Movie>>> = repository.getMoviesByGenre("")

    fun getMoviesByGenre(genre: String) : MutableLiveData<Result<List<Movie>>> {
        return repository.getMoviesByGenre(genre)
    }

    fun getMoviesByDirector(director: String) : MutableLiveData<Result<List<Movie>>> {
        return repository.getMoviesByDirector(director)
    }

    fun getMoviesByActor(actor: String) : MutableLiveData<Result<List<Movie>>> {
        return repository.getMoviesByActor(actor)
    }

    fun getMoviesByDate(date: String) : MutableLiveData<Result<List<Movie>>> {
        return repository.getMoviesByDate(date)
    }
}