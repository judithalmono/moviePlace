package com.example.movieplace.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieplace.MovieRepository
import com.example.movieplace.data.Result
import com.example.movieplace.data.model.Movie

class MovieViewModel: ViewModel() {
    private var repository: MovieRepository = MovieRepository()
    var movies = MutableLiveData<Result<List<Movie>>>()

    /**
     * gets the movies from the repository
     */
    fun getTopMovies() {
        Log.d("MARC", repository.getTopMovies().value.toString())
        movies.value = repository.getTopMovies().value
    }

    fun getRecommendMovies() {
        Log.d("PAU", repository.getRecommendMovies().value.toString())
        movies.value = repository.getRecommendMovies().value
    }
}