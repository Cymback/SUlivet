package com.example.caspergrosslarsen.sulivet.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.caspergrosslarsen.sulivet.R
import com.google.firebase.auth.FirebaseAuth
import timber.log.Timber

class ForgotPasswordActivity : AppCompatActivity() {

    private val TAG = "ForgotPasswordActivity"

    // UI

    private var etEmail: EditText? = null
    private var btnForgot: Button? = null

    // Firebase

    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)


        initialise()


    }

    private fun initialise() {
        etEmail = findViewById<View>(R.id.et_email) as EditText
        btnForgot = findViewById<View>(R.id.btn_forgot_email) as Button

        mAuth = FirebaseAuth.getInstance()

        btnForgot!!.setOnClickListener { sendPasswordResetEmail() }
    }

    private fun sendPasswordResetEmail() {
        val email = etEmail?.text.toString()

        if (!TextUtils.isEmpty(email)) {
            mAuth!!
                    .sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val message = "Email sent."
                            Timber.log(5, message)
                            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                            updateUi()
                        } else {
                            Timber.log(6, task.exception!!.message)
                            Toast.makeText(this, "No user found with this email", Toast.LENGTH_SHORT).show()
                        }
                    }

        } else {
            Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUi() {
        val intent = Intent(this@ForgotPasswordActivity, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

}
