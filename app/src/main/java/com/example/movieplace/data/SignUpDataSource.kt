package com.example.movieplace.data

import android.content.ContentValues.TAG
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.example.movieplace.common.Constants
import com.example.movieplace.data.model.BasicUser
import com.example.movieplace.data.model.Exist
import com.example.movieplace.data.model.Movie
import com.example.movieplace.data.model.SignUpUserData
import com.example.movieplace.data.retrofit.SignUpService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.*

class SignUpDataSource {

    private var _result = MutableLiveData<ResultSignUp>()
    val result: LiveData<ResultSignUp> = _result // aquest es observat per Repository

    private lateinit var firebaseAuth: FirebaseAuth
    private var retrofit: Retrofit =
        Retrofit.Builder().baseUrl(Constants().BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    private var signUpService: SignUpService = retrofit.create(SignUpService::class.java)

    /**
     * performs the Sign-Up on Firebase and the Back-end
     *
     * creates a user on firebase using the e-mail and password
     * sends a Firebase confirmation e-mail
     * creates a user on our database with all the data
     *
     * Observes the result of both user creations and sets the live data accordingly (success/error, and further info)
     * is supposed to put the appropriate error messages in the live data, but for now puts some error messages on screen
     *
     * @param email user's email
     * @param username user's username
     * @param password
     */
    fun signUp(email: String, username: String, password: String?) {

        try {
            firebaseAuth = Firebase.auth
            if (password != null) {
                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) { // Sign in success
                        Log.d("Sign-up", "createUserWithEmail:success")

                        val user = firebaseAuth.currentUser
                        user!!.sendEmailVerification().addOnCompleteListener { task2 ->
                            if (task2.isSuccessful) {
                                Log.d("Verification email", "Email sent.")
                            }
                        }

                        // parlar amb el nostre client
                        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                            if (!task.isSuccessful) {
                                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                                return@OnCompleteListener
                            }

                            // Get new FCM registration token
                            val token = task.result

                            signUpBack(username, password, email)
                        })
                    } else { // error a Firebase
                        Log.w("Sign-up", "createUserWithEmail:failure", task.exception)

                        _result.value = ResultSignUp(error = task.exception)
                    }
                }
            }
        } catch (e: Throwable) {
            _result.value = ResultSignUp(error = e as Exception) // cast!
        }
    }

    private fun signUpBack(username: String, password: String, email: String) {
        val user = BasicUser(username, password, email)
        val call: Call<ResponseBody> = signUpService.signUp(user)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    _result.value = ResultSignUp(success = true)
                } else {
                    _result.value = ResultSignUp(error = Exception("response received. Error in the server"))
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                t.printStackTrace()
                Log.w("Sign-up-back", "createUserWithEmail:failure", t.cause)
                _result.value = ResultSignUp(error = Exception("connection error. Server not reached"))
            }
        })
    }

    fun existsUser(username: String) : MutableLiveData<Result<Boolean>> {
        val result = MutableLiveData<Result<Boolean>>()
        val call: Call<Exist> = signUpService.existsUser(username)
        call.enqueue(object : Callback<Exist> {
            override fun onResponse(call: Call<Exist>, response: Response<Exist>) {
                if (response.isSuccessful) {
                    Log.d("DataSource", response.body().toString())
                    result.value = Result.Success(response.body()!!.exists)
                } else {
                    result.value = Result.Error(IOException("Error getting info1"))
                }
            }

            override fun onFailure(call: Call<Exist>, t: Throwable) {
                Log.d("GET", t.toString())
                result.value = Result.Error(IOException("Error getting info3"))
            }
        })
        return result
    }
}