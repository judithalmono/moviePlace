package com.example.movieplace.data.retrofit

import com.example.movieplace.common.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginClient {
    private lateinit var instance: LoginClient
    private var loginServices: LoginService? = null
    private var retrofit: Retrofit? = null

    /**
     * Inits all configuration, such as the baseURL or the GSon converter
     */
    init {
        retrofit = Retrofit.Builder()
            .baseUrl(Constants().BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        loginServices = retrofit!!.create(LoginService::class.java)
    }

    /**
     * Returns the instance of the client
     */
    fun getInstance(): LoginClient {
        return instance
    }

    /**
     * Returns the instance of the service
     */
    fun getLoginService(): LoginService? {
        return loginServices
    }
}
