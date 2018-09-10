package com.example.caspergrosslarsen.sulivet.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.caspergrosslarsen.sulivet.R
import com.example.caspergrosslarsen.sulivet.R.string.email
import com.example.caspergrosslarsen.sulivet.R.string.password
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text
import timber.log.Timber

class LoginActivity : AppCompatActivity() {

    private val TAG = "LoginActivity"

    // Global

    private var email: String? = null
    private var password: String? = null


    // UI

    lateinit var tvForgotPassword: TextView
    lateinit var etEmail: EditText
    lateinit var etPassword: EditText

    lateinit var btnLogin: Button
    lateinit var btnCreateAccount: Button

    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        initialise()

    }

    private fun initialise() {

        tvForgotPassword = findViewById<View>(R.id.forgot_password) as TextView
        etEmail = findViewById<View>(R.id.et_password) as EditText
        etPassword = findViewById<View>(R.id.et_password) as EditText

        btnLogin = findViewById<View>(R.id.btn_login) as Button


        mAuth = FirebaseAuth.getInstance()

        tvForgotPassword!!
                .setOnClickListener {
                    startActivity(Intent(this@LoginActivity, ForgotPasswordActivity::class.java))
                }

        btnLogin!!.setOnClickListener { loginUser() }

    }

    private fun loginUser() {
        email = etEmail?.text.toString()
        password = etPassword?.text.toString()

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {

            // progress bar here

            Timber.log(4, "Logging in user")

            mAuth!!.signInWithEmailAndPassword(email!!, password!!)
                    .addOnCompleteListener(this) { task ->
                        // Progress bar
                        if (task.isSuccessful) {
                            Timber.log(5, "signInWithEmail:success")
                            updateUI()
                        } else {
                            Timber.log(6, "signInWithEmail:failure", task.exception)
                            Toast.makeText(this@LoginActivity, "Authenciation failed.", Toast.LENGTH_SHORT).show()

                        }
                    }
        } else {
            Toast.makeText(this, "Enter all details please", Toast.LENGTH_SHORT).show()

        }
    }

    private fun updateUI() {
        val intent = Intent(this@LoginActivity, MenuActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}


