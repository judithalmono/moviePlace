package com.example.movieplace

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import androidx.lifecycle.MutableLiveData
import com.example.movieplace.data.model.Movie
import com.example.movieplace.data.Result
import com.example.movieplace.data.retrofit.MoviesClient
import retrofit2.Response
import java.io.IOException

class MovieRepository {

    var movie: MutableLiveData<List<Movie>>? = null
    private val moviesClient = MoviesClient()
    private val moviesService = moviesClient.getMoviesService()

    /**
     * It gets all the movies
     * @return the mutable livedata list of categories
     */
    fun getTopMovies(): MutableLiveData<Result<List<Movie>>> {
        val result = MutableLiveData<Result<List<Movie>>>()
        val call: Call<List<Movie>> = moviesService!!.getTopMovies()
        call.enqueue(object : Callback<List<Movie>> {
            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                if (response.isSuccessful) {
                    result.value = Result.Success(response.body() as List<Movie>)
                } else result.value = Result.Error(IOException("Error getting info"))
            }

            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                // Error en la connexion
                Log.d("GET", "Error getting info")
                result.value = Result.Error(IOException("Error getting info"))
            }
        })
        return result
    }

    fun getRecommendMovies(): MutableLiveData<Result<List<Movie>>> {
        val result = MutableLiveData<Result<List<Movie>>>()
        val call: Call<List<Movie>> = moviesService!!.getRecommendMovies()
        call.enqueue(object : Callback<List<Movie>> {
            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                if (response.isSuccessful) {
                    result.value = Result.Success(response.body() as List<Movie>)
                } else result.value = Result.Error(IOException("Error getting info"))
            }

            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                // Error en la connexion
                Log.d("GET", "Error getting info")
                result.value = Result.Error(IOException("Error getting info"))
            }
        })
        return result
    }

}