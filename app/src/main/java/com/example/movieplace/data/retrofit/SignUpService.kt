package com.example.movieplace.data.retrofit

import com.example.movieplace.data.model.BasicUser
import com.example.movieplace.data.model.Exist
import com.example.movieplace.data.model.Movie
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SignUpService {

    @POST("signUp")
    fun signUp( @Body user: BasicUser): Call<ResponseBody>

    @GET("existUser/[{username}")
    fun existsUser(@Path("username") username: String): Call<Exist>

}