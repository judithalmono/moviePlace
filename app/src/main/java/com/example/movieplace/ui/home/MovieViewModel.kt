package com.example.movieplace.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieplace.MovieRepository
import com.example.movieplace.data.Result
import com.example.movieplace.data.model.Movie

class MovieViewModel: ViewModel() {
    private var repository: MovieRepository = MovieRepository()
    var movies : MutableLiveData<Result<List<Movie>>> = repository.getTopMovies()

    /**
     * gets the movies from the repository
     */
    fun getTopMovies() : MutableLiveData<Result<List<Movie>>> {
        return repository.getTopMovies()
    }

    fun getRecommendMovies() : MutableLiveData<Result<List<Movie>>> {
        return repository.getRecommendMovies()
    }
}