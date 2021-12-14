package com.example.movieplace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.View.GONE
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.movieplace.ui.login.Login
import com.example.movieplace.ui.login.LoginViewModel
import com.example.movieplace.ui.login.LoginViewModelFactory

class ForgotPassword : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel

    private lateinit var editTextEmail: EditText
    private lateinit var btnGetPassword: Button
    private lateinit var btnToSignIn: TextView

    private lateinit var root: View
    private lateinit var loading: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        editTextEmail = findViewById(R.id.editTextEmail)
        root = findViewById(R.id.activity_forgotPassword)
        btnGetPassword = findViewById(R.id.btn_GetPassword)
        btnToSignIn = findViewById(R.id.textSignIn)
        loading = findViewById(R.id.progressBar)

        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())[LoginViewModel::class.java]

        btnGetPassword.setOnClickListener {
            if (isValidEmail()) {
                loading.visibility = View.VISIBLE
                loginViewModel.recoverPassword(editTextEmail.text.toString()).observe(
                    this, {
                        loading.visibility = GONE
                        Log.d("IT", it)
                        Toast.makeText(applicationContext, "Enters $it", Toast.LENGTH_LONG).show()
                        if (it == "OK") {
                            Toast.makeText(applicationContext, "Email sent. Please check your email inbox", Toast.LENGTH_LONG).show()
                            startActivity(Intent(this, Login::class.java))
                        } else if (it == "ERROR") {
                            editTextEmail.setBackgroundResource(R.drawable.background_edt_wrong)
                            Toast.makeText(this, "Email not found!", Toast.LENGTH_LONG).show()
                        }
                    }
                )

            } else {
                editTextEmail.setBackgroundResource(R.drawable.background_edt_wrong)
                Toast.makeText(this, "Email not correct!", Toast.LENGTH_LONG).show()
            }
        }

        btnToSignIn.setOnClickListener {
            loading.visibility = View.VISIBLE
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        root.setOnClickListener {
            val view = this.currentFocus
            if (view != null) {
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }
    }

    private fun isValidEmail(): Boolean {
        return editTextEmail.text.contains('@') && Patterns.EMAIL_ADDRESS.matcher(editTextEmail.text).matches()
    }
}

