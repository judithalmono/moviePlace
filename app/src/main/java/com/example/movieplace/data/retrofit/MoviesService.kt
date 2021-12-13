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
     * Gets the info of the user
     *
     * @param user
     */

    @GET("/searchUsr/{user}")
    fun getInfoUser(@Path("user") user: String): Call<User>


    /**
     * Sets the username of the user
     *
     * @param user
     */

    @Headers("Content-Type: application/json")
    @POST("/updateUser")
    fun setUsername(@Body user: Username): Call<ResponseBody>

    /**
     * Sets the full name of the user
     *
     * @param user
     */

    @Headers("Content-Type: application/json")
    @POST("/updateFullName")
    fun setFullName(@Body user: FullName): Call<ResponseBody>

    /**
     * Sets the password of the user
     *
     * @param user
     */

    @POST("/updatePassword")
    fun setPassword(@Body user: Password): Call<ResponseBody>

    /**
     * Sets the email of the user
     *
     * @param user
     */

    @POST("/updateMail")
    fun setEmail(@Body user: Email): Call<ResponseBody>

    /**
     * Sets the phone number of the user
     *
     * @param user
     */

    @POST("/updateTelefon")
    fun setTelefon(@Body user: Phone): Call<ResponseBody>

    /**
     * Sets the brirth date of the user
     *
     * @param user
     */

    @POST("/updateBirth")
    fun setBirth(@Body user: Birth): Call<ResponseBody>

    /**
     * Sets the address of the user
     *
     * @param user
     */

    @POST("/updateAddress")
    fun setAddress(@Body user: Address): Call<ResponseBody>

    /**
     * Sets the sex of the user
     *
     * @param user
     */

    @POST("/updateSex")
    fun setSex(@Body user: Sex): Call<ResponseBody>

    /**
     * Sets the photo of the user
     *
     * @param user
     */

    @POST("/insertImg")
    fun setProfilePhoto(@Body user: ProfilePhoto) : Call<ResponseBody>

    /**
     * Deletes the photo of the user
     *
     * @param user
     */

    @POST("/insertImg")
    fun deleteProfilePhoto(@Body username: Delete) : Call<ResponseBody>

    /**
     * Update the genre of the user
     *
     * @param user
     */

    @POST("/anadirGen1")
    fun setGenre1(@Body user: NewGenre): Call<ResponseBody>

    /**
     * Update the genre of the user
     *
     * @param user
     */

    @POST("/anadirGen2")
    fun setGenre2(@Body user: NewGenre): Call<ResponseBody>

    /**
     * Update the directors of the user
     *
     * @param user
     */

    @POST("/anadirDir1")
    fun setDirector1(@Body user: NewDirector): Call<ResponseBody>

    /**
     * Update the directors of the user
     *
     * @param user
     */

    @POST("/anadirDir2")
    fun setDirector2(@Body user: NewDirector): Call<ResponseBody>

    /**
     * Update the actors of the user
     *
     * @param user
     */

    @POST("/anadirAct1")
    fun setActor1(@Body user: NewActor): Call<ResponseBody>

    /**
     * Update the actors of the user
     *
     * @param user
     */

    @POST("/anadirAct2")
    fun setActor2(@Body user: NewActor): Call<ResponseBody>

    /**
     * Update the composers of the user
     *
     * @param user
     */

    @POST("/anadirComp1")
    fun setCompositor1(@Body user: NewCompositor): Call<ResponseBody>

    /**
     * Update the composers of the user
     *
     * @param user
     */

    @POST("/anadirComp2")
    fun setCompositor2(@Body user: NewCompositor): Call<ResponseBody>

    /**
     * Deletes the genre1 of the user
     *
     * @param user
     */

    @POST("/delGen1")
    fun deleteGenre1(@Body username: Delete) : Call<ResponseBody>

    /**
     * Deletes the genre2 of the user
     *
     * @param user
     */

    @POST("/delGen2")
    fun deleteGenre2(@Body username: Delete) : Call<ResponseBody>

    /**
     * Deletes the director1 of the user
     *
     * @param user
     */

    @POST("/delDir1")
    fun deleteDirector1(@Body username: Delete) : Call<ResponseBody>

    /**
     * Deletes the director2 of the user
     *
     * @param user
     */

    @POST("/delDir2")
    fun deleteDirector2(@Body username: Delete) : Call<ResponseBody>

    /**
     * Deletes the actor1 of the user
     *
     * @param user
     */

    @POST("/delAct1")
    fun deleteActor1(@Body username: Delete) : Call<ResponseBody>

    /**
     * Deletes the actor2 of the user
     *
     * @param user
     */

    @POST("/delAct2")
    fun deleteActor2(@Body username: Delete) : Call<ResponseBody>

    /**
     * Deletes the composer1 of the user
     *
     * @param user
     */

    @POST("/delComp1")
    fun deleteComposer1(@Body username: Delete) : Call<ResponseBody>

    /**
     * Deletes the composer2 of the user
     *
     * @param user
     */

    @POST("/delComp2")
    fun deleteComposer2(@Body username: Delete) : Call<ResponseBody>

    /**
     * Send a suggestion
     *
     * @param user
     */

    @POST("/suggest")
    fun setSuggest(@Body user: Suggest): Call<ResponseBody>

}