package com.example.movieplace.data.retrofit

import com.example.movieplace.data.model.Movie
import com.example.movieplace.data.model.Scene
import com.example.movieplace.data.model.User
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface MoviesService {

    /**
     * Search a movie by its name
     *
     * @param name's movie
     */

    @GET("/search/{name}")
    fun getMovieByName(@Path("name") name: String): Call<Movie>

    /**
     * Filter movies by genre
     *
     * @param gen it's the genre of the movie
     */

    @GET("/searchgenre/{gen}")
    fun getMoviesByGenre(@Path("gen") gen: String): Call<List<Movie>>

    /**
     * Filter movies by date
     *
     * @param date it's the date of the movie
     */

    @GET("/searchdate/{date}")
    fun getMoviesByDate(@Path("date") date: String): Call<List<Movie>>

    /**
     * Filter movies by director
     *
     * @param dir it's the director of the movie
     */

    @GET("/searchdirector/{dir}")
    fun getMoviesByDirector(@Path("dir") dir: String): Call<List<Movie>>

    /**
     * Get the scenes of a movie by its id
     *
     * @param id of the movie
     */

    @GET("/getScenes/{id}")
    fun getScenesByID(@Path("id") id: Int): Call<List<Scene>>

    /**
     * Sort movies by user ubication
     */

    @GET("/sortUbi")
    fun getTopMovies(): Call<List<Movie>>

    /**
     * Sort movies by users rate
     */

    @GET("sortRate")
    fun getRecommendMovies(): Call<List<Movie>>

    /**
     * Gets the password of the user
     *
     * @param user
     */

    @GET("/login/{user}")
    fun getPasswordUser(@Path("user") user: String): Call<User>

    /**
     * Gets the password of the user
     *
     * @param user
     */

    @GET("/login/{user}")
    fun getInfoUser(@Path("user") user: String): Call<User>


    /**
     * Gets the password of the user
     *
     * @param usr
     * @param newus
     */

    @PUT("/updateUser")
    fun setUsername(@Body usr: String, newus : String): Call<ResponseBody>

    /**
     * Gets the password of the user
     *
     * @param usr
     * @param newname
     */

    @PUT("/updateUser")
    fun setFullName(@Body usr: String, newname : String): Call<ResponseBody>

    /**
     * Gets the password of the user
     *
     * @param usr
     * @param newpsw
     */

    @PUT("/updateUser")
    fun setPassword(@Body usr: String, newpsw : String): Call<ResponseBody>

    /**
     * Gets the password of the user
     *
     * @param usr
     * @param newmail
     */

    @PUT("/updateUser")
    fun setEmail(@Body usr: String, newmail : String): Call<ResponseBody>

    /**
     * Gets the password of the user
     *
     * @param usr
     * @param newtel
     */

    @PUT("/updateUser")
    fun setTelefon(@Body usr: String, newtel : String): Call<ResponseBody>

    /**
     * Gets the password of the user
     *
     * @param usr
     * @param newnbd
     */

    @PUT("/updateUser")
    fun setBirth(@Body usr: String, newbd : String): Call<ResponseBody>

    /**
     * Gets the password of the user
     *
     * @param usr
     * @param newaddr
     */

    @PUT("/updateUser")
    fun setAddress(@Body usr: String, newaddr : String): Call<ResponseBody>

    /**
     * Gets the password of the user
     *
     * @param usr
     * @param newsex
     */

    @PUT("/updateUser")
    fun setSex(@Body usr: String, newsex : String): Call<ResponseBody>

}