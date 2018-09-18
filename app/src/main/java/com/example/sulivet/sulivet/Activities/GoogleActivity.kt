package com.example.sulivet.sulivet.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

import android.widget.Toast
import com.example.sulivet.sulivet.R

import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task


class GoogleActivity : AppCompatActivity() {


    lateinit var button: Button
    private lateinit var gso: GoogleSignInOptions
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN: Int = 1

    lateinit var signOut: Button
    lateinit var btnToLogin: Button
    lateinit var btnToCreate: Button

    companion object {
        const val GLGTOWIZ = "GLGTOWIZ" // key identifier for toWizardTwo intent

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_login)

        val signIn = findViewById<View>(R.id.sign_in_btn) as Button
        val btnToLogin = findViewById<View>(R.id.to_login_with_email_btn) as Button
        val btnToCreate = findViewById<View>(R.id.create_account_btn)


        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        signIn.setOnClickListener {
            signIn()
        }

        btnToLogin.setOnClickListener {
            val myIntent = Intent(this@GoogleActivity, LoginActivity::class.java)
            startActivity(myIntent)
        }

        btnToCreate.setOnClickListener {
            val myIntent = Intent(this@GoogleActivity, CreateAccountActivity::class.java)
            startActivity(myIntent)
        }
    }

    private fun signIn() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {

            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleResult(task)
        } else {
            Toast.makeText(this, "Problem in execution order :(", Toast.LENGTH_LONG).show()
        }
    }

    private fun handleResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount = completedTask.getResult(ApiException::class.java)
            // updateUI(account)
            // handle account info here


            val myIntent = Intent(this@GoogleActivity, SplashScreenActivity::class.java)
            startActivity(myIntent)

        } catch (e: ApiException) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
        }
    }
}

//    private fun updateUI(account: GoogleSignInAccount) {
//        val dispTxt = findViewById<View>(R.id.dispTxt) as TextView
//        dispTxt.text = account.displayName
//        signOut.visibility = View.VISIBLE
//        signOut.setOnClickListener { view: View? ->
//            mGoogleSignInClient.signOut().addOnCompleteListener { task: Task<Void> ->
//                if (task.isSuccessful) {
//                    dispTxt.text = " "
//                    signOut.visibility = View.INVISIBLE
//                    signOut.isClickable = false
//                }
//            }
//        }
//    }




