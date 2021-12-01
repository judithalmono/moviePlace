package com.example.movieplace

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import androidx.lifecycle.MutableLiveData
import com.example.movieplace.data.model.Movie
import com.example.movieplace.data.Result
import com.example.movieplace.data.model.Scene
import com.example.movieplace.data.model.User
import com.example.movieplace.data.retrofit.MoviesClient
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.IOException

class MovieRepository {

    var movie: MutableLiveData<List<Movie>>? = null
    private val moviesClient = MoviesClient()
    private val moviesService = moviesClient.getMoviesService()

    /**
     * It gets the movie named "name"
     *
     * @param name of the movie
     * @return the mutable livedata of movie named "name"
     */

    fun getMovieByName(name: String): MutableLiveData<Result<Movie>> {
        val result = MutableLiveData<Result<Movie>>()
        val call: Call<Movie> = moviesService!!.getMovieByName(name)
        call.enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    result.value = Result.Success(response.body() as Movie)
                } else result.value = Result.Error(IOException("Error getting info"))
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                // Error en la connexion
                Log.d("GET", "Error getting info")
                result.value = Result.Error(IOException("Error getting info"))
            }
        })
        return result
    }

    /**
     * It gets all movies with the same genre
     *
     * @param gen is the genre of the movie
     * @return the mutable livedata list of movies with genre "gen"
     */

    fun getMoviesByGenre(gen: String): MutableLiveData<Result<List<Movie>>> {
        val result = MutableLiveData<Result<List<Movie>>>()
        val call: Call<List<Movie>> = moviesService!!.getMoviesByGenre(gen)
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

    /**
     * It gets all movies with the same date
     *
     * @param date is the date of the movie
     * @return the mutable livedata list of movies with date "date"
     */

    fun getMoviesByDate(date: String): MutableLiveData<Result<List<Movie>>> {
        val result = MutableLiveData<Result<List<Movie>>>()
        val call: Call<List<Movie>> = moviesService!!.getMoviesByDate(date)
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

    /**
     * It gets all movies with the same director
     *
     * @param dir is the director of the movie
     * @return the mutable livedata list of movies with director "dir"
     */

    fun getMoviesByDirector(dir: String): MutableLiveData<Result<List<Movie>>> {
        val result = MutableLiveData<Result<List<Movie>>>()
        val call: Call<List<Movie>> = moviesService!!.getMoviesByDirector(dir)
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

    /**
     * It gets all scenes of the movie with the id
     *
     * @param id is the id of the movie
     * @return the mutable livedata list of scenes with movie "id"
     */

    fun getScenesByID(id: Int): MutableLiveData<Result<List<Scene>>> {
        val result = MutableLiveData<Result<List<Scene>>>()
        val call: Call<List<Scene>> = moviesService!!.getScenesByID(id)
        call.enqueue(object : Callback<List<Scene>> {
            override fun onResponse(call: Call<List<Scene>>, response: Response<List<Scene>>) {
                if (response.isSuccessful) {
                    result.value = Result.Success(response.body() as List<Scene>)
                } else result.value = Result.Error(IOException("Error getting info 1"))
            }
            override fun onFailure(call: Call<List<Scene>>, t: Throwable) {
                // Error en la connexion
                Log.d("GET", t.toString())
                result.value = Result.Error(IOException("Error getting info 3"))
            }
        })
        return result
    }

    /**
     * It gets all movies sorted by users' rate
     *
     * @return the mutable livedata list of movies sorted by users' rate
     */

//    fun getTopMovies(onSuccess: List<Movie>) -> Unit, onError: (Throwable) -> Unit) {
//        val call = api.getMovies()
//        call.enqueue(object: Callback<MovieResponse> {
//            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
//                onError(t)
//            }
//
//            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
//                if (response.isSuccessful) {
//                    val movies = response.body()?.results
//                    onSuccess(movies ?: listOf())
//                }
//            }
//        })
//
//    }

    fun getTopMovies(): MutableLiveData<Result<List<Movie>>> {
        val result = MutableLiveData<Result<List<Movie>>>()
        val call: Call<List<Movie>> = moviesService!!.getTopMovies()
        call.enqueue(object : Callback<List<Movie>> {
            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                if (response.isSuccessful) {
                    result.value = Result.Success(response.body() as List<Movie>)
                } else result.value = Result.Error(IOException("Error getting info 1"))
            }

            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                // Error en la connexion
                Log.d("GET", "Error getting info 2")
                result.value = Result.Error(IOException("Error getting info 3"))
            }
        })
        return result
    }

    /**
     * It gets all movies sorted by user location
     *
     * @return the mutable livedata list of movies sorted by user location
     */

    fun getRecommendMovies(): MutableLiveData<Result<List<Movie>>> {
        val result = MutableLiveData<Result<List<Movie>>>()
        val call: Call<List<Movie>> = moviesService!!.getRecommendMovies()
        call.enqueue(object : Callback<List<Movie>> {
            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                if (response.isSuccessful) {
                    result.value = Result.Success(response.body() as List<Movie>)
                } else result.value = Result.Error(IOException("Error getting info 1"))
            }

            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                // Error en la connexion
                Log.d("GET", "Error getting info 2")
                result.value = Result.Error(IOException("Error getting info 3"))
            }
        })
        return result
    }

    /**
     * It gets the User of the user "user"
     *
     * @return the mutable livedata of User "user"
     */

    fun getPasswordUser(user: String): MutableLiveData<Result<User>> {
        val result = MutableLiveData<Result<User>>()
        val call: Call<User> = moviesService!!.getPasswordUser(user)
        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    result.value = Result.Success(response.body() as User)
                } else result.value = Result.Error(IOException("Error getting info"))
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                // Error en la connexion
                Log.d("GET", "Error getting info")
                result.value = Result.Error(IOException("Error getting info"))
            }
        })
        return result
    }

}