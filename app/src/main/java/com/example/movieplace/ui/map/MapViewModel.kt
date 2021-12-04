package com.example.movieplace.ui.map

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieplace.MovieRepository
import com.example.movieplace.data.Result
import com.example.movieplace.data.model.Scene

class MapViewModel : ViewModel() {

    private var repository: MovieRepository = MovieRepository()
//    var scenes : MutableLiveData<Result<List<Scene>>> = repository.getAllScenes()

    /**
     * gets the movies from the repository
     */
//    fun getAllScenes() : MutableLiveData<Result<List<Scene>>> {
//        return scenes
//    }
}