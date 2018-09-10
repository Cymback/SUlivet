package com.example.caspergrosslarsen.sulivet.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.caspergrosslarsen.sulivet.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import timber.log.Timber

class CreateAccountActivity : AppCompatActivity() {

    // UI ELEMENTS

    private var etEmail: EditText? = null
    private var etPassword: EditText? = null
    private var btnCreateAccount: Button? = null
    private var mProgressBar: ProgressBar? = null


    // FIREBASE ELEMENTS

    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null

    private val TAG = "CreateAccountActivity"

    // GLOBAL VARIABLES

    private var email: String? = null
    private var password: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        intialise()
    }

    private fun intialise() {

        // Initialising

        etEmail = findViewById<View>(R.id.et_email) as EditText
        etPassword = findViewById<View>(R.id.et_password) as EditText
        btnCreateAccount = findViewById<View>(R.id.create_account_btn) as Button

        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference!!.child("Users")
        mAuth = FirebaseAuth.getInstance()



        btnCreateAccount!!.setOnClickListener { createNewAccount() }
    }

    private fun createNewAccount() {

        // Retrieving current string value of the fields edittext (USER + PASS)

        email = etEmail?.text.toString()
        password = etPassword?.text.toString()

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {

            mAuth!!
                    .createUserWithEmailAndPassword(email!!, password!!)
                    .addOnCompleteListener(this) { task
                        ->
                        mProgressBar

                        if (task.isSuccessful) {
                            Timber.log(1, "createUserWithEmail:success")

                            val userId = mAuth!!.currentUser!!.uid


                            // Verifying email

                            verifyEmail()

                            // Update user profile information

                            val currentUserDb = mDatabaseReference!!.child(userId)
                            currentUserDb.child("email").setValue(email)
                            currentUserDb.child("password").setValue(password)

                            updateUserInfoAndUI()

                            Toast.makeText(this, "Login created succesfully", Toast.LENGTH_SHORT).show()

                        } else {
                            Timber.log(2, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(this@CreateAccountActivity, "Authentication failed", Toast.LENGTH_SHORT).show()

                        }

                    }
        }


    }

    private fun updateUserInfoAndUI() {

        // Also responsible for starting new activity

        val intent = Intent(this@CreateAccountActivity, MenuActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) // the FLAG_ACTIVITY_CLEAR_TOP flag clears the CreateAccountActivity from stack so that if user press back from MenuActivity, he should not be taken back to CreateAccountActivity.
        startActivity(intent)


    }

    private fun verifyEmail() {
        val mUser = mAuth!!.currentUser
        mUser!!.sendEmailVerification()
                .addOnCompleteListener(this) { task ->

                    if (task.isSuccessful) {
                        Toast.makeText(this@CreateAccountActivity, "Verification email sent to " + mUser.email, Toast.LENGTH_SHORT).show()
                    } else {
                        Timber.log(3, "sendEmailVerification", task.exception)
                        Toast.makeText(this@CreateAccountActivity,
                                "Failed to send verification email.",
                                Toast.LENGTH_SHORT).show()
                    }
                }


    }
}











