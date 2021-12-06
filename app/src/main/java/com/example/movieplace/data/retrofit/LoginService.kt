package com.example.movieplace.data.retrofit

//import com.example.movieplace.data.model.BasicUser
import com.example.movieplace.data.model.Password
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface LoginService {

    @GET("/getEmail/{username}")
    fun getEmail(@Path("username") username: String): Call<String>

//    @GET("/existUser/{username}")
//    fun existUser(@Path("username") username: String): Call<BasicUser>
//
//    @POST("/signUp")
//    fun signIn(@Body user: BasicUser): Call<String>
}