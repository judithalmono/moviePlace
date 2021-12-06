package com.example.movieplace.data.retrofit

import com.example.movieplace.data.model.SignUpUserData
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface SignUpService {

    @POST("users/{username}/create")
    fun createProfile(@Path("username") username: String, @Body signUpUserData: SignUpUserData): Call<ResponseBody>
}