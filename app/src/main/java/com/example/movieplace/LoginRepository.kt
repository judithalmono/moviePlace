package com.example.movieplace

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieplace.data.LoginDataSource
import com.example.movieplace.data.Result
import com.example.movieplace.data.model.EmailUser
import com.example.movieplace.data.model.LoggedInUser
import com.example.movieplace.data.retrofit.LoginClient
import okhttp3.ResponseBody
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
    fun login(email: String, password: String): LiveData<Result<LoggedInUser>> {
        return dataSource.login(email, password)
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
        val qu = dataSource.recoverPassword(email)
        Log.d("A ver1", qu.value.toString())
        return qu
    }

    fun deleteAccount() {
    }

    fun getEmail(username: String): MutableLiveData<Result<EmailUser>> {
        val result = MutableLiveData<Result<EmailUser>>()
        val call: Call<EmailUser> = loginServices!!.getEmail(username)
        call.enqueue(object : Callback<EmailUser> {
            override fun onResponse(call: Call<EmailUser>, response: Response<EmailUser>) {
                if (response.isSuccessful) {
                    if (response.body() != null)
                        result.value = Result.Success(response.body() as EmailUser)
                    else result.value = Result.Success(EmailUser(""))
                } else result.value = Result.Error(IOException("Error getting info 1"))
            }

            override fun onFailure(call: Call<EmailUser>, t: Throwable) {
                // Error en la connexion
                Log.d("GET", t.toString())
                result.value = Result.Error(IOException("Error getting info 3"))
            }
        })
        return result
    }
}