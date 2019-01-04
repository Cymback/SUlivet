package com.example.sulivet.sulivet.Fragments

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.sulivet.sulivet.Activities.ForgotPasswordActivity
import com.example.sulivet.sulivet.R
import com.example.sulivet.sulivet.bottomnavigation.ui.TakeMeAwayActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.login_fragment.*
import timber.log.Timber
import java.util.*

class LoginFragment : Fragment() {

    // Global variables

    private var progressBar: ProgressBar? = null
    private var forgotpass: TextView? = null

    // Facebook
    private lateinit var loginButton: LoginButton
    private var callbackManager: CallbackManager? = null

    //Buttons
    private var btnLogin: Button? = null


    companion object {
        private const val TAG = "LoginFragment"
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.login_fragment, container, false)

        progressBar = view.findViewById(R.id.frag_login_progressbar)
        btnLogin = view.findViewById(R.id.login_button_login)
        forgotpass = view.findViewById(R.id.frag_login_forgot_pass_text)
        val fblogin: Button = view.findViewById(R.id.facebook_login)


        btnLogin!!.setOnClickListener {
            performLogin()
        }

        forgotpass!!.setOnClickListener {
            val intent = Intent(activity, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }


        // Facebook login
        fblogin.setOnClickListener {


            progressBar!!.indeterminateDrawable.setColorFilter(ContextCompat.getColor(this.context!!, R.color.greysigninsignup), PorterDuff.Mode.SRC_IN)
            progressBar!!.visibility = View.VISIBLE

            callbackManager = CallbackManager.Factory.create()
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"))
            LoginManager.getInstance().registerCallback(callbackManager,
                    object : FacebookCallback<LoginResult> {
                        override fun onSuccess(loginResult: LoginResult) {
                            Timber.d("Facebook token: %s", loginResult.accessToken.token)

                            Toast.makeText(context, "Welcome ", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(context, TakeMeAwayActivity::class.java))
                        }

                        override fun onCancel() {
                            progressBar!!.visibility = View.GONE
                            Timber.d("Facebook onCancel.")

                        }

                        override fun onError(error: FacebookException) {
                            progressBar!!.visibility = View.GONE
                            Timber.d("MainActivityFacebook onError.")

                        }
                    })
        }
        return view

    }

    private fun performLogin() {
        val email = email_edittext_login.text.toString()
        val password = password_edittext_login.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(activity, "Please fill out email/pw.", Toast.LENGTH_SHORT).show()
            return
        }

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (!it.isSuccessful) return@addOnCompleteListener
                    startActivity(Intent(context, TakeMeAwayActivity::class.java))
                    Log.d("Login", "Successfully logged in: ${it.result!!.user.uid}")
                }
                .addOnFailureListener {
                    Toast.makeText(activity, "Failed to log in: ${it.message}", Toast.LENGTH_SHORT).show()
                }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        callbackManager?.onActivityResult(requestCode, resultCode, data)
    }
}







