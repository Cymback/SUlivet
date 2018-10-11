package com.example.sulivet.sulivet.Fragments

import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.annotation.NonNull
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.sulivet.sulivet.Activities.ForgotPasswordActivity
import com.example.sulivet.sulivet.Activities.MenuActivity
import com.example.sulivet.sulivet.MenuActivities.ChallengeModeActivity
import com.example.sulivet.sulivet.R
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.login_fragment.*
import timber.log.Timber
import java.util.*

class LoginFragment : Fragment() {

    // Global

    private var email: String? = null
    private var password: String? = null
    private var progressBar: ProgressBar? = null
    private val EMAIL = "email"


    // UI

    private lateinit var tvForgotPassword: TextView
    private lateinit var tvCreateAccount: TextView


    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText

    private lateinit var btnLogin: Button


    // Facebook
    private lateinit var loginButton: LoginButton
    private var callbackManager: CallbackManager? = null


    private var mAuth: FirebaseAuth? = null

    companion object {
        private val TAG = "LoginFragment"
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.login_fragment, container, false)

        FbisLoggedIn()


        // tvForgotPassword.setOnClickListener { forgotPassword() }

        progressBar = view.findViewById(R.id.frag_login_progressbar)

        etEmail = view.findViewById(R.id.frag_login_email_view)
        etPassword = view.findViewById(R.id.frag_login_password_view)

        val btnLogin: TextView? = view.findViewById(R.id.frag_login_login_btn)
        val fblogin: Button = view.findViewById(R.id.facebook_login)


        mAuth = FirebaseAuth.getInstance()

        btnLogin?.setOnClickListener {
            startActivity(Intent(activity, ForgotPasswordActivity::class.java))
        }

        btnLogin!!.setOnClickListener { loginUser() }


        // Facebook

        fblogin.setOnClickListener {
            callbackManager = CallbackManager.Factory.create()
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"))
            LoginManager.getInstance().registerCallback(callbackManager,
                    object : FacebookCallback<LoginResult> {
                        override fun onSuccess(loginResult: LoginResult) {
                            Timber.d("Facebook token: " + loginResult.accessToken.token)

                            Toast.makeText(context, "Welcome ", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(context, MenuActivity::class.java))
                        }

                        override fun onCancel() {
                            Timber.d("Facebook onCancel.")

                        }

                        override fun onError(error: FacebookException) {
                            Timber.d("MainActivity" + "Facebook onError.")

                        }
                    })
        }
        return view

    }

    fun FbisLoggedIn() {


    }

    private fun forgotPassword() {
        val intent = Intent(activity, ForgotPasswordActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }


    private fun loginUser() {

        progressBar!!.indeterminateDrawable.setColorFilter(ContextCompat.getColor(this.context!!, R.color.redcolor), PorterDuff.Mode.SRC_IN)
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


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        callbackManager?.onActivityResult(requestCode, resultCode, data)
    }
}





