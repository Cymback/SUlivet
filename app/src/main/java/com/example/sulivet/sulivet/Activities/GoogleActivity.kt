package com.example.sulivet.sulivet.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar

import android.widget.Toast
import com.example.sulivet.sulivet.Model.User
import com.example.sulivet.sulivet.R

import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import timber.log.Timber


class GoogleActivity : AppCompatActivity() {


    lateinit var button: Button
    private lateinit var gso: GoogleSignInOptions
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN: Int = 1

    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null

    // progressbar


    companion object {
        const val GLGTOWIZ = "GLGTOWIZ" // key identifier for toWizardTwo intent

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_login)

        val signIn = findViewById<View>(R.id.sign_in_btn)
        val btnToLogin = findViewById<View>(R.id.to_login_with_email_btn)
        val btnToCreate = findViewById<View>(R.id.create_account_btn)

        val progressBar = findViewById<ProgressBar>(R.id.google_progressbar)




        mAuth = FirebaseAuth.getInstance()

        if (mAuth?.currentUser == null) {

            gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build()

            mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
            signIn.setOnClickListener {
                if (progressBar != null) {
                    
                }
                signIn()
            }

        } else {

            startActivity(Intent(this@GoogleActivity, MenuActivity::class.java))
            finishAffinity()
            // ^ removes activites in the backstack
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

        val task: Task<GoogleSignInAccount>? = null
        if (requestCode == RC_SIGN_IN) {

            updateUI()



        } else {
            Toast.makeText(this, "Problem in execution order :(", Toast.LENGTH_LONG).show()
            Timber.log(11, task?.exception)


        }
    }

    private fun updateUI() {



        mDatabase = FirebaseDatabase.getInstance()

        mDatabaseReference = mDatabase?.reference?.child("Users")

        val mUser = mAuth!!.currentUser

        val user = User()

        user.apply {
            mUser?.uid
            mUser?.email
        }

        mDatabaseReference?.setValue(user)!!.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                startActivity(Intent(this@GoogleActivity, MenuActivity::class.java))
            } else {
                Timber.log(12, "Failed to set value of user in DB")
            }

        }

    }
}

