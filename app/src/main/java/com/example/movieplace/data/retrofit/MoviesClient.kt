package com.example.movieplace.data.retrofit

import com.example.movieplace.common.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesClient {
    private lateinit var instance: MoviesClient
    private var moviesService: MoviesService? = null
    private var retrofit: Retrofit? = null

    /**
     * Inits all configuration, such as the baseURL or the GSon converter
     */
    init {
        retrofit = Retrofit.Builder()
            .baseUrl(Constants().BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        moviesService = retrofit!!.create(MoviesService::class.java)
    }

    /**
     * Returns the instance of the client
     */
    fun getInstance(): MoviesClient {
        return instance
    }

    /**
     * Returns the instance of the service
     */
    fun getMoviesService(): MoviesService? {
        return moviesService
    }
}