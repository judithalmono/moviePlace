package com.example.movieplace.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.movieplace.ForgotPassword
import com.example.movieplace.Home
import com.example.movieplace.R
import com.example.movieplace.common.Constants
import com.example.movieplace.common.SharedPreferenceManager
import com.example.movieplace.data.Result
import com.example.movieplace.ui.signUp.SignUp

class Login : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel

    private lateinit var editTextUser: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnToSignUp: TextView
    private lateinit var btnRecoverPassword: TextView
    private lateinit var root: View
    private var email: String = ""
    private lateinit var visibilityPassword: Button
    private lateinit var loading: ProgressBar
    private var visibility: Boolean = false
    private lateinit var username: String


    /**
     * It is executed when the activity is launched for first time or created again following
     * activities lifecycle.
     * @param savedInstanceState is the instance of the saved State of the activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

//        if (!SharedPreferenceManager.getBooleanValue(Constants().PREF_IS_NOT_FIRST_TIME_OPENING_APP)) {
//            Log.d("first time2", "2 is_first_time = "+ SharedPreferenceManager.getBooleanValue(Constants().PREF_IS_NOT_FIRST_TIME_OPENING_APP))
//            val intent = Intent(this, OnboardingActivity::class.java)
//            startActivity(intent)
//            finish()
//        }

//        if (SharedPreferenceManager.getStringValue(Constants().PREF_EMAIL) != null) {
//            val intent = Intent(this, Home::class.java)
//            startActivity(intent)
//            finish()
//        }

        setUp()
        startObservers()
        editTextsChanges()

        visibilityPassword.setOnClickListener {

            if (visibility) visibilityPassword.background =
                ContextCompat.getDrawable(this, R.drawable.visibilityon)
            else visibilityPassword.background =
                ContextCompat.getDrawable(this, R.drawable.visibilityoff)
            visibility = !visibility

            editTextPassword.inputType =
                if (editTextPassword.inputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD) {
                    InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                } else InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

            editTextPassword.setSelection(editTextPassword.text.length)

        }

        btnToSignUp.setOnClickListener {
            loading.visibility = View.VISIBLE
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        root.setOnClickListener {
            val view = this.currentFocus
            root.clearFocus()
            if (view != null) {
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }

        btnRecoverPassword.setOnClickListener {
            loading.visibility = View.VISIBLE
            val intent = Intent(this, ForgotPassword::class.java)
            startActivity(intent)
        }
    }

    private fun setUp() {
        editTextUser = findViewById(R.id.editTextUser)
        editTextPassword = findViewById(R.id.editTextPassword)
        root = findViewById(R.id.activity_login)
        btnRecoverPassword = findViewById(R.id.textForgot)
        btnLogin = findViewById(R.id.buttonLogin)
        btnToSignUp = findViewById(R.id.textSignUp)
        loading = findViewById(R.id.progressBar)
        visibilityPassword = findViewById(R.id.visibilityPassword)
        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())[LoginViewModel::class.java]
    }

    private fun startObservers() {
        loginViewModel.loginFormState.observe(
            this@Login,
            Observer {
                val loginState = it ?: return@Observer

                // disable login button unless both username / password is valid
                btnLogin.isEnabled = loginState.isDataValid

            }
        )

        loginViewModel.loginResult.observe(
            this@Login,
            Observer {
                val loginResult = it ?: return@Observer
                Log.d("RESULT", loginResult.toString())
                if (loginResult.error != null) {
                    loading.visibility = View.GONE
                    showLoginFailed(loginResult.error)
                }
                if (loginResult.success != null) {
                    updateUiWithUser(loginResult.success)
                }

                setResult(Activity.RESULT_OK)
                // Complete and destroy login activity once successful
//                 finish()
            }
        )
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        model.data.observe(
            this@Login,
            {
                loading.visibility = View.GONE
                val welcome = getString(R.string.welcome)
                when {
                    it.errorLogin != null && it.errorLogin == R.string.login_failed_email -> {
                        loading.visibility = GONE
                        Toast.makeText(applicationContext, getString(R.string.login_failed_email), Toast.LENGTH_LONG).show()
                    }
                    it.errorLogin != null && it.errorLogin == R.string.login_failed_login -> {
                        loading.visibility = GONE
                        Toast.makeText(applicationContext, getString(R.string.login_failed_login), Toast.LENGTH_LONG).show()
                    }
                    it.errorLogin != null && it.errorLogin == R.string.login_failed_server -> loading.visibility = VISIBLE
                    else -> {
                        loading.visibility = GONE
                        val intent = Intent(this, Home::class.java)
                        startActivity(intent)
                        Toast.makeText(
                            applicationContext,
                            "$welcome $username",
                            Toast.LENGTH_LONG
                        ).show()
                        finish()
                    }
                }
            }
        )
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        editTextPassword.setBackgroundResource(R.drawable.background_edt_wrong)
        editTextUser.setBackgroundResource(R.drawable.background_edt_wrong)
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }

    private fun editTextsChanges() {
        editTextUser.afterTextChanged {
            email = ""
            editTextUser.setBackgroundResource(R.drawable.background_btn_edit)
        }
        editTextUser.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus){
                Log.d("SE VA", "el foco")
                username = editTextUser.text.toString()
                loginViewModel.getEmail(editTextUser.text.toString()).observe(
                    this, {
                        if (it is Result.Success) {
                            email = it.data.email
                        }
                    }
                )
                loginViewModel.loginDataChanged(
                    email,
                    editTextPassword.text.toString()
                )
            }
        }

        editTextPassword.apply {
            setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    loginViewModel.loginDataChanged(
                        email,
                        editTextPassword.text.toString()
                    )
                }
            }

            afterTextChanged {
                setBackgroundResource(R.drawable.background_btn_edit)
                loginViewModel.loginDataChanged(
                    email,
                    editTextPassword.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            email,
                            editTextPassword.text.toString()
                        )
                }
                false
            }

            btnLogin.setOnClickListener {
                btnLogin.clearFocus()
                loading.visibility = View.VISIBLE
                loginViewModel.login(
                    email,
                    editTextPassword.text.toString()
                )
            }
        }
    }

    private fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
    }
}

