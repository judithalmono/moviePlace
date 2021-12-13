package com.example.movieplace.ui.scenes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieplace.MovieRepository
import com.example.movieplace.data.Result
import com.example.movieplace.data.model.Scene
import com.example.movieplace.data.model.Vote

class SceneViewModel: ViewModel() {
    private var repository: MovieRepository = MovieRepository()

    /**
     * gets the scenes of the movie "ID" from the repository
     */
    fun getScenesByID(id: Int): MutableLiveData<Result<List<Scene>>> {
        return repository.getScenesByID(id)
    }

    fun updateVote(vote: Vote): MutableLiveData<Result<String>> {
        return repository.updateVote(vote)
    }

}