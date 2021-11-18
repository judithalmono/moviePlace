package com.example.movieplace.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieplace.MovieRepository
import com.example.movieplace.data.Result
import com.example.movieplace.data.model.Movie

class MovieViewModel: ViewModel() {
    private var repository: MovieRepository = MovieRepository()
    lateinit var movies: MutableLiveData<Result<List<Movie>>>

    /**
     * gets the movies from the repository
     */
    fun getTopMovies() {
        movies = repository.getTopMovies()
    }

    fun getRecommendMovies() : MutableLiveData<Result<List<Movie>>> {
        return repository.getRecommendMovies()
    }
}