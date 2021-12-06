package com.example.movieplace.data.retrofit

import com.example.movieplace.data.model.*
import okhttp3.RequestBody
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

    @GET("/sortRate")
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

    @GET("/searchUsr/{user}")
    fun getInfoUser(@Path("user") user: String): Call<User>


    /**
     * Gets the password of the user
     *
     * @param user
     */

    @Headers("Content-Type: application/json")
    @POST("/updateUser")
    fun setUsername(@Body user: Username): Call<ResponseBody>

    /**
     * Gets the password of the user
     *
     * @param user
     */

    @Headers("Content-Type: application/json")
    @POST("/updateFullName")
    fun setFullName(@Body user: FullName): Call<ResponseBody>

    /**
     * Gets the password of the user
     *
     * @param user
     */

    @POST("/updatePassword")
    fun setPassword(@Body user: Password): Call<ResponseBody>

    /**
     * Gets the password of the user
     *
     * @param user
     */

    @POST("/updateMail")
    fun setEmail(@Body user: Email): Call<ResponseBody>

    /**
     * Gets the password of the user
     *
     * @param user
     */

    @POST("/updateTelefon")
    fun setTelefon(@Body user: Phone): Call<ResponseBody>

    /**
     * Gets the password of the user
     *
     * @param user
     */

    @POST("/updateBirth")
    fun setBirth(@Body user: Birth): Call<ResponseBody>

    /**
     * Gets the password of the user
     *
     * @param user
     */

    @POST("/updateAddress")
    fun setAddress(@Body user: Address): Call<ResponseBody>

    /**
     * Gets the password of the user
     *
     * @param user
     */

    @POST("/updateSex")
    fun setSex(@Body user: Sex): Call<ResponseBody>

    /**
     * Gets the password of the user
     *
     * @param user
     */
    /*
    @Multipart
    @POST("/updatePhoto")
    fun setProfilePhoto(@Body user: Sex, @Part(value = "file\"; filename=\"photo.jpeg\" ") file: RequestBody): Call<ResponseBody>
    */


}