package com.example.movieplace

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieplace.data.ResultSignUp
import com.example.movieplace.data.SignUpDataSource
import androidx.lifecycle.Observer
import com.example.movieplace.data.Result
import com.example.movieplace.data.model.BasicUser
import com.example.movieplace.data.model.Exist
import com.example.movieplace.data.model.SignUpUserData
import com.example.movieplace.data.retrofit.LoginClient
import com.example.movieplace.data.retrofit.SignUpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class SignUpRepository(val dataSource: SignUpDataSource) {

    private val signUpClient = SignUpClient()
    private val signUpService = signUpClient.getSignUpService()

    private val _result = MutableLiveData<ResultSignUp>()
    val result: LiveData<ResultSignUp> = _result

    /**
     * Propagates the Sign-up process to the Data Source
     *
     * makes the call to the Data Source and observes its live data for the result
     * Sets the Repository's live data according to that of the Data Source when it is ready
     * @param email user's email
     * @param username user's username
     * @param password
     * @param activity pointer to the activity, used by the observers
     */
    fun signUp(email: String, username: String, password: String?, activity: AppCompatActivity) {
        dataSource.result.observe(
            activity,
            Observer {
                val resultDS = it ?: return@Observer

                // _result.value = resultDS  //potser es pot substituir per això
                if (resultDS.error != null) {
                    _result.value = ResultSignUp(error = resultDS.error)
                }
                if (resultDS.success != null) {
                    _result.value = ResultSignUp(success = resultDS.success)
                }
                // aqui la activity fa mes coses q suposo q aqui no calen
                dataSource.result.removeObservers(activity)
            }
        )
        dataSource.signUp(email, username, password)
    }

    fun existsUser(username: String): MutableLiveData<Result<Boolean>> {
        return dataSource.existsUser(username)
    }


}