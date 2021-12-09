package com.example.movieplace

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import androidx.lifecycle.MutableLiveData
import com.example.movieplace.data.Result
import com.example.movieplace.data.model.*
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

    fun getAllScenes(): MutableLiveData<Result<List<Scene>>> {
        val result = MutableLiveData<Result<List<Scene>>>()
        val call: Call<List<Scene>> = moviesService!!.getAllScenes()
        call.enqueue(object : Callback<List<Scene>> {
            override fun onResponse(call: Call<List<Scene>>, response: Response<List<Scene>>) {
                if (response.isSuccessful) {
                    result.value = Result.Success(response.body() as List<Scene>)
                } else result.value = Result.Error(IOException("Error getting info 1"))
            }

            override fun onFailure(call: Call<List<Scene>>, t: Throwable) {
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

    /**
     * It gets the User of the user "user"
     *
     * @return the mutable livedata of User "user"
     */

    fun getInfoUser(user: String): MutableLiveData<Result<User>> {
        val result = MutableLiveData<Result<User>>()
        val call: Call<User> = moviesService!!.getInfoUser(user)
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

    /**
     * It sets the User of the user "user"
     *
     * @return the mutable livedata which will be updated with the result of the call
     */

    fun setUsername(user: Username): MutableLiveData<Result<String>> {
        val result = MutableLiveData<Result<String>>()
        val call: Call<ResponseBody> = moviesService!!.setUsername(user)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    Log.d("repo::setUsername", "response.code() == " + response.code())
                    if (response.code() == 200 || response.code() == 201) {
                        if (response.body() == null)
                            Log.d("repo::setUsername", "response.body() == null.")
                    Log.d("response", "setUsername response: is successful")
                    result.value = Result.Success(response.body().toString())
                    }
                    else {
                        Log.d("repo::setUsername", "response code not in (200, 201)")
                    }
                }
                else result.value = Result.Error(IOException("Error getting info"))
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                // Error en la connexion
                Log.d("GET", "Error getting info")
                result.value = Result.Error(IOException("setUsername Error getting info"))
            }
        })
        return result
    }

    /**
     * It sets the Full Name of the user "user"
     *
     * @return the mutable livedata which will be updated with the result of the call
     */
    fun setFullName(user: FullName): MutableLiveData<Result<String>> {
        val result = MutableLiveData<Result<String>>()
        val call: Call<ResponseBody> = moviesService!!.setFullName(user)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    Log.d("response", "setFullName response: is successful")
                    result.value = Result.Success(response.body().toString())
                } else result.value = Result.Error(IOException("Error getting info"))
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                // Error en la connexion
                Log.d("GET", "Error getting info")
                result.value = Result.Error(IOException("setFullName Error getting info"))
            }
        })
        return result
    }

    /**
     * It sets the Passowrd of the user "user"
     *
     * @return the mutable livedata which will be updated with the result of the call
     */

    fun setPassword(user: Password): MutableLiveData<Result<String>> {
        val result = MutableLiveData<Result<String>>()
        val call: Call<ResponseBody> = moviesService!!.setPassword(user)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    Log.d("response", "setPassword response: is successful")
                    result.value = Result.Success(response.body().toString())
                } else result.value = Result.Error(IOException("Error getting info"))
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                // Error en la connexion
                Log.d("GET", "Error getting info")
                result.value = Result.Error(IOException("setPassword Error getting info"))
            }
        })
        return result
    }

    /**
     * It sets the Email of the user "user"
     *
     * @return the mutable livedata which will be updated with the result of the call
     */

    fun setEmail(user: Email): MutableLiveData<Result<String>> {
        val result = MutableLiveData<Result<String>>()
        val call: Call<ResponseBody> = moviesService!!.setEmail(user)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    Log.d("response", "setEmail response: is successful")
                    result.value = Result.Success(response.body().toString())
                } else result.value = Result.Error(IOException("Error getting info"))
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                // Error en la connexion
                Log.d("GET", "Error getting info")
                result.value = Result.Error(IOException("setEmail Error getting info"))
            }
        })
        return result
    }

    /**
     * It sets the Phone number of the user "user"
     *
     * @return the mutable livedata which will be updated with the result of the call
     */

    fun setTelefon(user: Phone): MutableLiveData<Result<String>> {
        val result = MutableLiveData<Result<String>>()
        val call: Call<ResponseBody> = moviesService!!.setTelefon(user)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    Log.d("response", "setTelefon response: is successful")
                    result.value = Result.Success(response.body().toString())
                } else result.value = Result.Error(IOException("Error getting info"))
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                // Error en la connexion
                Log.d("GET", "Error getting info")
                result.value = Result.Error(IOException("setTelefon Error getting info"))
            }
        })
        return result
    }

    /**
     * It sets the Birth Date of the user "user"
     *
     * @return the mutable livedata which will be updated with the result of the call
     */

    fun setBirth(user: Birth): MutableLiveData<Result<String>> {
        val result = MutableLiveData<Result<String>>()
        val call: Call<ResponseBody> = moviesService!!.setBirth(user)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    Log.d("response", "setBirth response: is successful")
                    result.value = Result.Success(response.body().toString())
                } else result.value = Result.Error(IOException("Error getting info"))
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                // Error en la connexion
                Log.d("GET", "Error getting info")
                result.value = Result.Error(IOException("setBirth Error getting info"))
            }
        })
        return result
    }

    /**
     * It sets the Email of the user "user"
     *
     * @return the mutable livedata which will be updated with the result of the call
     */

    fun setAddress(user: Address): MutableLiveData<Result<String>> {
        val result = MutableLiveData<Result<String>>()
        val call: Call<ResponseBody> = moviesService!!.setAddress(user)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    Log.d("response", "setAddress response: is successful")
                    result.value = Result.Success(response.body().toString())
                } else result.value = Result.Error(IOException("Error getting info"))
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                // Error en la connexion
                Log.d("GET", "Error getting info")
                result.value = Result.Error(IOException("setAddress Error getting info"))
            }
        })
        return result
    }

    /**
     * It sets the Email of the user "user"
     *
     * @return the mutable livedata which will be updated with the result of the call
     */

    fun setSex(user: Sex): MutableLiveData<Result<String>> {
        val result = MutableLiveData<Result<String>>()
        val call: Call<ResponseBody> = moviesService!!.setSex(user)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    Log.d("response", "setSex response: is successful")
                    result.value = Result.Success(response.body().toString())
                } else result.value = Result.Error(IOException("Error getting info"))
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                // Error en la connexion
                Log.d("GET", "Error getting info")
                result.value = Result.Error(IOException("setSex Error getting info"))
            }
        })
        return result
    }

    /**
     * It sets the Fav Genres of the user "user"
     *
     * @return the mutable livedata which will be updated with the result of the call
     */

    fun setGenre(user: NewGenre): MutableLiveData<Result<String>> {
        val result = MutableLiveData<Result<String>>()
        val call: Call<ResponseBody> = moviesService!!.setGenre(user)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    Log.d("response", "setGenre response: is successful")
                    result.value = Result.Success(response.body().toString())
                } else result.value = Result.Error(IOException("Error getting info"))
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                // Error en la connexion
                Log.d("GET", "Error getting info")
                result.value = Result.Error(IOException("setGenre Error getting info"))
            }
        })
        return result
    }

    /**
     * It sets the Fav Directors of the user "user"
     *
     * @return the mutable livedata which will be updated with the result of the call
     */

    fun setDirector(user: NewDirector): MutableLiveData<Result<String>> {
        val result = MutableLiveData<Result<String>>()
        val call: Call<ResponseBody> = moviesService!!.setDirector(user)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    Log.d("response", "setDirector response: is successful")
                    result.value = Result.Success(response.body().toString())
                } else result.value = Result.Error(IOException("Error getting info"))
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                // Error en la connexion
                Log.d("GET", "Error getting info")
                result.value = Result.Error(IOException("setDirector Error getting info"))
            }
        })
        return result
    }

    /**
     * It sets the Fav Actors of the user "user"
     *
     * @return the mutable livedata which will be updated with the result of the call
     */

    fun setActor(user: NewActor): MutableLiveData<Result<String>> {
        val result = MutableLiveData<Result<String>>()
        val call: Call<ResponseBody> = moviesService!!.setActor(user)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    Log.d("response", "setActor response: is successful")
                    result.value = Result.Success(response.body().toString())
                } else result.value = Result.Error(IOException("Error getting info"))
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                // Error en la connexion
                Log.d("GET", "Error getting info")
                result.value = Result.Error(IOException("setActor Error getting info"))
            }
        })
        return result
    }

    /**
     * It sets the Fav Compositors of the user "user"
     *
     * @return the mutable livedata which will be updated with the result of the call
     */

    fun setCompositor(user: NewCompositor): MutableLiveData<Result<String>> {
        val result = MutableLiveData<Result<String>>()
        val call: Call<ResponseBody> = moviesService!!.setCompositor(user)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    Log.d("response", "setCompositor response: is successful")
                    result.value = Result.Success(response.body().toString())
                } else result.value = Result.Error(IOException("Error getting info"))
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                // Error en la connexion
                Log.d("GET", "Error getting info")
                result.value = Result.Error(IOException("setCompositor Error getting info"))
            }
        })
        return result
    }
}