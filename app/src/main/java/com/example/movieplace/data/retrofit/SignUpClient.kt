package com.example.movieplace.data.retrofit

import com.example.movieplace.common.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SignUpClient {
    private lateinit var instance: SignUpClient
    private var signUpservice: SignUpService? = null
    private var retrofit: Retrofit? = null

    /**
     * Inits all configuration, such as the baseURL or the GSon converter
     */
    init {
        retrofit = Retrofit.Builder()
            .baseUrl(Constants().BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        signUpservice = retrofit!!.create(SignUpService::class.java)
    }

    /**
     * Returns the instance of the client
     */
    fun getInstance(): SignUpClient {
        return instance
    }

    /**
     * Returns the instance of the service
     */
    fun getSignUpService(): SignUpService? {
        return signUpservice
    }
}
