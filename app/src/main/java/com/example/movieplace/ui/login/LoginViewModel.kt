package com.example.movieplace.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieplace.LoginRepository
import com.example.movieplace.R
import com.example.movieplace.common.Constants
import com.example.movieplace.common.SharedPreferenceManager
import com.example.movieplace.data.LoginDataSource
import com.example.movieplace.data.Result

class LoginViewModel(loginRepository: LoginRepository) : ViewModel() {

    private var loginRepository: LoginRepository = LoginRepository(LoginDataSource())

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    lateinit var recoverResult: LiveData<String>

    /**
     * It checks if the password introduced by the user is valid. Specifically checks if the sufficiently large
     */

    fun loginPasswordEquals(password: String, password2: String): Boolean {
        return password == password2
    }

    private fun isEmailValid(email: String): Boolean {
        return email.contains('@') && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

    fun getEmail(username: String): MutableLiveData<Result<String>> {
        return loginRepository.getEmail(username)
    }

    fun login(email: String, password: String) {
        // can be launched in a separate asynchronous job
        val result = loginRepository.login(email, password)

        if (result is Result.Success) {
            _loginResult.value =
                LoginResult(success = LoggedInUserView(data = result.data))
            SharedPreferenceManager.setStringValue(Constants().PREF_EMAIL, email)
            SharedPreferenceManager.setStringValue(Constants().PREF_PROVIDER, Constants().PREF_PROVIDER_PASSWORD)
        } else {
            if (result is Result.Error) {
                _loginResult.value = LoginResult(error = R.string.login_failed)
            }
        }
    }

    fun recoverPassword(email: String) {
        recoverResult = loginRepository.recoverPassword(email)
    }

    fun loginDataChanged(password: String)  {
        if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    //    fun signIn(user: BasicUser) : MutableLiveData<Result<String>> {
//        return loginRepository.signIn(user)
//    }

}
