package com.example.movieplace.ui.signUp

import android.util.Log
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieplace.R
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.movieplace.SignUpRepository
import com.example.movieplace.data.Result
import com.example.movieplace.data.model.EmailUser
import com.example.movieplace.data.model.Exist

class SignUpViewModel (private val signUpRepository: SignUpRepository) : ViewModel() {

    private val _signUpForm = MutableLiveData<SignUpFormState>()
    val signUpFormState: LiveData<SignUpFormState> = _signUpForm

    private val _signUpResult = MutableLiveData<SignUpResult>()
    val signUpResult: LiveData<SignUpResult> = _signUpResult // aquest és observat per SignUpActivity.

    /**
     * Initiates the Sign-up process
     *
     * makes the call to the Repository and observes its live data for the result.
     * Sets the ViewModel's live data according to that of the Repository when it is ready
     * @param email user's email
     * @param username user's username
     * @param password
     * @param activity pointer to the activity, used by the observers
     */

    fun signUp (email: String, username: String, password: String?, activity: AppCompatActivity) {
        // can be launched in a separate asynchronous job
        signUpRepository.result.observe(
            activity,
            Observer {
                val resultRepo = it ?: return@Observer
                Log.d("RESULT S", resultRepo.success.toString())
                if (resultRepo.error != null) {
                    val msg: String = resultRepo.error.toString()
                    // println("msg = $msg")

                    when { // quan el backend implementi noves excepcions, haurem d'afegir entrades aqui
                        msg == "com.google.firebase.auth.FirebaseAuthUserCollisionException: The email address is already in use by another account."
                        -> _signUpResult.value = SignUpResult(error = R.string.email_taken)
                        msg == "com.google.firebase.auth.FirebaseAuthInvalidCredentialsException: The email address is badly formatted."
                        -> _signUpResult.value = SignUpResult(error = R.string.sign_up_bad_email_format)
                        msg == "com.google.firebase.FirebaseNetworkException: A network error (such as timeout, interrupted connection or unreachable host) has occurred."
                        -> _signUpResult.value = SignUpResult(error = R.string.sign_up_fb_connection_error)
                        (msg == "connection error. Server not reached" || msg == "java.lang.Exception: connection error. Server not reached") // crec q nomes serà el 2n.
                        -> _signUpResult.value = SignUpResult(error = R.string.sign_up_connection_error)
                        (msg == "response received. Error in the server" || msg == "java.lang.Exception: response received. Error in the server")
                        -> _signUpResult.value = SignUpResult(error = R.string.sign_up_server_error)
                        else
                        -> _signUpResult.value = SignUpResult(error = R.string.unknown_sign_up_error)
                    }
                }
                if (resultRepo.success != null) {
                    _signUpResult.value = SignUpResult(success = resultRepo.success)
                }
                // aqui la activity fa mes coses q suposo q aqui no calen

                signUpRepository.result.removeObservers(activity)
            }
        )

        // val fecha = birthDate?.let { getDateFromString(it) }

        signUpRepository.signUp(email, username, password, activity)
    }

    /**
     * Updates the live data _signUpForm depending on whether the data in the parameters has the proper format
     *
     * @param email email field string
     * @param username username field string
     * @param password password field string
     */
    fun signupDataChanged(email: String, username: String, password: String, password2: String) {
        if (!isEmailValid(email)) {
            _signUpForm.value = SignUpFormState(emailError = R.string.invalid_email)
        } else if (!isPasswordValid(password)) {
            _signUpForm.value = SignUpFormState(passwordError = R.string.invalid_password)
        } else if (!isPasswordValid(password2)) {
            _signUpForm.value = SignUpFormState(passwordError2 = R.string.invalid_password)
        } else {
            _signUpForm.value = SignUpFormState(isDataValid = true)
        }
    }

    /**
     * Returns true <=> the email string follows the expected e-mail format
     *
     * @param email email field string
     * @return Returns true <=> the input email string follows the expected e-mail format (something@something.something)
     */
    private fun isEmailValid(email: String): Boolean {
        return email.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(email).matches() // de stackoverflow
    }

    /**
     * Returns true <=> the username string follows the expected format
     *
     * @param username username field string
     * @return Returns true <=> the username string follows the expected format (it's not empty)
     */
    fun isUserNameValid(username: String): MutableLiveData<Result<Boolean>> {
        return signUpRepository.existsUser(username)
    }

    /**
     * Returns true <=> the password string follows the expected format
     *
     * It's just a placeholder for now
     * @param password password field string
     * @return Returns true <=> the password string follows the expected format (it is over 5 char's long)
     */
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

//    fun signUpBD(user: BasicUser): MutableLiveData<Result<String>>{
//        return signUpRepository.signUpBD(user)
//    }
}