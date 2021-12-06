package com.example.movieplace

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieplace.data.LoginDataSource
import com.example.movieplace.data.Result
import com.example.movieplace.data.model.LoggedInUser
import com.example.movieplace.data.retrofit.LoginClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class LoginRepository(val dataSource: LoginDataSource) {

    private val loginClient = LoginClient()
    private val loginServices = loginClient.getLoginService()

    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        user = null
    }

    /**
     * Calls to the data source to log out and deletes the info of the user
     */
    fun logout() {
        user = null
        dataSource.logout()
    }

    /**
     * Calls to the data source to log in and if successful, saves de result into de loggedInUser
     * @param email is the email of the user
     * @param password is the password of the user
     * @return the result with a live data of the log in
     */
    fun login(email: String, password: String): Result<LiveData<LoggedInUser>> {
        val result = dataSource.login(email, password)

        if (result is Result.Success) {
            setLoggedInUser(result.data.value)
        }

        return result
    }

    /**
     * Sets the logged in user
     */
    private fun setLoggedInUser(loggedInUser: LoggedInUser?) {
        this.user = loggedInUser
    }

    /**
     * It calls the data source to send the reset password email
     */
    fun recoverPassword(email: String): LiveData<String> {
        return dataSource.recoverPassword(email)
    }

    fun deleteAccount() {
    }

    fun getEmail(username: String): MutableLiveData<Result<String>> {
        val result = MutableLiveData<Result<String>>()
        val call: Call<String> = loginServices!!.getEmail(username)
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    result.value = Result.Success(response.body() as String)
                } else result.value = Result.Error(IOException("Error getting info 1"))
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                // Error en la connexion
                Log.d("GET", t.toString())
                result.value = Result.Error(IOException("Error getting info 3"))
            }
        })
        return result
    }

//    private fun setLoggedInUser(loggedInUser: LoggedInUser?) {
//        this.user = loggedInUser
//    }

}