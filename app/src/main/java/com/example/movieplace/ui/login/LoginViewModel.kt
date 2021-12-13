package com.example.movieplace.ui.login

import android.content.Context
import android.util.Log
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieplace.LoginRepository
import com.example.movieplace.R
import com.example.movieplace.common.Constants
import com.example.movieplace.common.SharedPreferenceManager
import com.example.movieplace.data.LoginDataSource
import com.example.movieplace.data.Result
import com.example.movieplace.data.model.EmailUser

class LoginViewModel(loginRepository: LoginRepository) : ViewModel() {

    private var loginRepository: LoginRepository = LoginRepository(LoginDataSource())

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    /**
     * It checks if the password introduced by the user is valid. Specifically checks if the sufficiently large
     */

    private fun isUserValid(email: String): Boolean {
        return email != ""
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

    fun getEmail(username: String): MutableLiveData<Result<EmailUser>> {
        return loginRepository.getEmail(username)
    }

    fun login(email: String, password: String, context: AppCompatActivity) {
        // can be launched in a separate asynchronous job
        loginRepository.login(email, password).observe(
            context, {
                val value = it
                if (value is Result.Success) {
                    _loginResult.value =
                        LoginResult(success = LoggedInUserView(data = value.data))
                    SharedPreferenceManager.setStringValue(Constants().PREF_EMAIL, email)
                    SharedPreferenceManager.setStringValue(Constants().PREF_PROVIDER, Constants().PREF_PROVIDER_PASSWORD)
                }
                else if (value is Result.Error) {
                    _loginResult.value = LoginResult(error = R.string.login_failed)
                }
            }
        )
    }

    fun recoverPassword(email: String): LiveData<String> {
        return loginRepository.recoverPassword(email)
    }

    fun loginDataChanged(username: String, password: String)  {
        if (!isUserValid(username)) {
            _loginForm.value = LoginFormState(emailError = R.string.invalid_username)
        }
        if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }
}
