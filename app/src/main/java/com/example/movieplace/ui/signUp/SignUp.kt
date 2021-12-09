package com.example.movieplace.ui.signUp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.movieplace.R
import com.example.movieplace.data.model.BasicUser
import com.example.movieplace.ui.login.Login

class SignUp : AppCompatActivity() {

    private lateinit var editTextUser: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextPassword2: EditText

    private lateinit var btnSignUp: Button
    private lateinit var btnToLogin: TextView
    private lateinit var visibilityPassword: Button
    private lateinit var visibilityPassword2: Button
    private lateinit var loading: ProgressBar
    private lateinit var root: View

    private val activity: SignUp = this

    private var visibility: Boolean = false
    private var visibility2: Boolean = false

    private lateinit var signUpViewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        setUp()
        startObservers()
        editTextsChanges()

        //Habilitar/Deshabilitar vista contraseña
        visibilityPassword.setOnClickListener {

            if (visibility) visibilityPassword.background = ContextCompat.getDrawable(this, R.drawable.visibilityon)
            else visibilityPassword.background = ContextCompat.getDrawable(this, R.drawable.visibilityoff)
            visibility = !visibility

            editTextPassword.inputType = if (editTextPassword.inputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD) {
                InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

            editTextPassword.setSelection(editTextPassword.text.length)

        }
        visibilityPassword2.setOnClickListener {

            if (visibility2) visibilityPassword2.background = ContextCompat.getDrawable(this, R.drawable.visibilityon)
            else visibilityPassword2.background = ContextCompat.getDrawable(this, R.drawable.visibilityoff)
            visibility2 = !visibility2

            editTextPassword2.inputType = if (editTextPassword2.inputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD) {
                InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

            editTextPassword2.setSelection(editTextPassword2.text.length)

        }

        //Ir al Login
        btnToLogin.setOnClickListener {
            loading.visibility = View.VISIBLE
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        //Permitir esconder el teclado
        root.setOnClickListener {
            val view = this.currentFocus
            if (view != null) {
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
            root.clearFocus()
        }
    }

    private fun setUp() {
        editTextUser = findViewById(R.id.editTextUser)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        editTextPassword2 = findViewById(R.id.editTextPassword2)
        root = findViewById(R.id.activity_signup)
        btnSignUp = findViewById(R.id.buttonSignUp)
        btnToLogin = findViewById(R.id.textSignIn)
        visibilityPassword = findViewById(R.id.visibilityPassword)
        visibilityPassword2 = findViewById(R.id.visibilityPassword2)
        loading = findViewById(R.id.progressBar)

        signUpViewModel = ViewModelProvider(this, SignUpViewModelFactory())[SignUpViewModel::class.java]
    }

    private fun startObservers() {

        signUpViewModel.signUpFormState.observe(
            this@SignUp,
            Observer {
                val signUpStateVM = it ?: return@Observer

                // disable login button unless all fields are valid

                btnSignUp.isEnabled = signUpStateVM.isDataValid == true

                if (signUpStateVM.emailError != null) { // si hi ha error
                    editTextEmail.error = getString(signUpStateVM.emailError)
                }
                if (signUpStateVM.usernameError != null) {
                    editTextUser.error = getString(signUpStateVM.usernameError)
                }
                if (signUpStateVM.passwordError != null) {
                    editTextPassword.error = getString(signUpStateVM.passwordError)
                }
                if (signUpStateVM.passwordError2 != null) {
                    editTextPassword2.error = getString(signUpStateVM.passwordError2)
                }
                if (editTextPassword.text.toString() != editTextPassword2.text.toString()) {
                    editTextPassword.setBackgroundResource(R.drawable.background_edt_wrong)
                    editTextPassword2.setBackgroundResource(R.drawable.background_edt_wrong)
                    btnSignUp.isEnabled = false
                }
                else {
                    editTextPassword.setBackgroundResource(R.drawable.background_btn_edit)
                    editTextPassword2.setBackgroundResource(R.drawable.background_btn_edit)
                }

            }
        )

        signUpViewModel.signUpResult.observe(
            this@SignUp,
            Observer {
                val signUpResultVM = it ?: return@Observer
                Log.d("TODO OK", signUpResultVM.success.toString())
                Log.d("TODO NO", signUpResultVM.error.toString())
                loading.visibility = View.GONE
                if (signUpResultVM.error != null) {
                    showSignUpFailed(signUpResultVM.error)
                }
                if (signUpResultVM.success != null) {
                    showSuccessAndProceed()
                }
                setResult(Activity.RESULT_OK)

            }
        )

    }

    private fun editTextsChanges() {

        editTextEmail.afterTextChanged {
            signUpViewModel.signupDataChanged(
                editTextEmail.text.toString(),
                editTextUser.text.toString(),
                editTextPassword.text.toString(),
                editTextPassword2.text.toString()
            )
        }

        editTextUser.afterTextChanged {
//            signUpViewModel.existsUser(editTextUser.text.toString()).observe(
//                this.
//                {
//
//                }
//            )
            signUpViewModel.signupDataChanged(
                editTextEmail.text.toString(),
                editTextUser.text.toString(),
                editTextPassword.text.toString(),
                editTextPassword2.text.toString()
            )
        }

        editTextPassword.afterTextChanged {
                signUpViewModel.signupDataChanged(
                    editTextEmail.text.toString(),
                    editTextUser.text.toString(),
                    editTextPassword.text.toString(),
                    editTextPassword2.text.toString()
                )
        }

        editTextPassword2.apply {
            afterTextChanged {
                signUpViewModel.signupDataChanged(
                    editTextEmail.text.toString(),
                    editTextUser.text.toString(),
                    editTextPassword.text.toString(),
                    editTextPassword2.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        signUpViewModel.signUp(
                            editTextEmail.text.toString(),
                            editTextUser.text.toString(),
                            editTextPassword.text.toString(),
                            activity
                        )
                }
                false
            }

            btnSignUp.setOnClickListener {
                loading.visibility = View.VISIBLE
                signUpViewModel.signUp(
                    editTextEmail.text.toString(),
                    editTextUser.text.toString(),
                    editTextPassword.text.toString(),
                    activity
                )
            }
        }
    }

    private fun showSuccessAndProceed() {
        val emailConfirmationMessage = getString(R.string.emailConfirmationMessage)

        // ensenyar missatge de welcome a baix
        Toast.makeText(
            applicationContext,
            emailConfirmationMessage,
            Toast.LENGTH_LONG
        ).show()

        // canviar a pantalla de LogIn.
        loading.visibility = View.VISIBLE
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }

    private fun showSignUpFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
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