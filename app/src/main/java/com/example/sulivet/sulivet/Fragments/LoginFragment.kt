package com.example.sulivet.sulivet.Fragments

import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.sulivet.sulivet.Activities.ForgotPasswordActivity
import com.example.sulivet.sulivet.Activities.MenuActivity
import com.example.sulivet.sulivet.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import timber.log.Timber

class LoginFragment : Fragment() {

    // Global

    private var email: String? = null
    private var password: String? = null
    private var progressBar: ProgressBar? = null


    // UI

    private lateinit var tvForgotPassword: TextView
    private lateinit var tvCreateAccount: TextView


    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText

    private lateinit var btnLogin: Button

    private var mAuth: FirebaseAuth? = null

    companion object {
        private val TAG = "LoginFragment"
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.login_fragment, container, false)

        tvForgotPassword = view.findViewById(R.id.frag_login_forgot_password_text)

        progressBar = view.findViewById(R.id.frag_login_progressbar)

        etEmail = view.findViewById(R.id.frag_login_email_view)
        etPassword = view.findViewById(R.id.frag_login_password_view)

        val btnLogin: TextView? = view.findViewById(R.id.frag_login_login_btn)

        mAuth = FirebaseAuth.getInstance()

        btnLogin?.setOnClickListener {
            startActivity(Intent(activity, ForgotPasswordActivity::class.java))
        }

        btnLogin!!.setOnClickListener { loginUser() }

        tvForgotPassword.setOnClickListener { forgotPassword() }


        return view

    }


    private fun forgotPassword() {
        val intent = Intent(activity, ForgotPasswordActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }


    private fun loginUser() {

        progressBar!!.indeterminateDrawable.setColorFilter(ContextCompat.getColor(this!!.context!!, R.color.redcolor), PorterDuff.Mode.SRC_IN)
        progressBar!!.visibility = View.VISIBLE

        email = etEmail.text.toString()
        password = etPassword.text.toString()


        if (email!!.isEmpty()) {
            etEmail.error = "Email is required"
            etEmail.requestFocus()
            progressBar!!.visibility = View.GONE

            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.error = "Please enter a valid email"
            etEmail.requestFocus()
            progressBar!!.visibility = View.GONE

            return
        }

        if (password!!.isEmpty()) {
            etPassword.error = "Password is required"
            etPassword.requestFocus()
            progressBar!!.visibility = View.GONE

            return

        }




        mAuth!!.signInWithEmailAndPassword(email!!, password!!)
                .addOnCompleteListener(this.activity!!) { task ->

                    if (task.isSuccessful) {
                        Timber.log(5, "signInWithEmail:success")
                        updateUI()

                        progressBar!!.visibility = View.GONE


                    } else {
                        Timber.log(6, "signInWithEmail:failure", task.exception)
                        Toast.makeText(activity, "Authentication failed.", Toast.LENGTH_SHORT).show()
                        progressBar!!.visibility = View.GONE

                    }

                }

    }

    private fun updateUI() {
        val intent = Intent(activity, MenuActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}





