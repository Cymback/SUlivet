package com.example.sulivet.sulivet.Fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat.startActivity
import android.text.TextUtils
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.sulivet.sulivet.Activities.MenuActivity
import com.example.sulivet.sulivet.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import timber.log.Timber

class SignUpFragment : Fragment() {

    private var etUsername: EditText? = null
    private var etEmail: EditText? = null
    private var etPassword: EditText? = null

    private var btnCreateAccount: TextView? = null


    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null


    private var email: String? = null
    private var password: String? = null

    companion object {

        private val TAG = "Sign up"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.signup_fragment, container, false)



        etUsername = view.findViewById(R.id.frag_create_acc_desired_username)
        etEmail = view.findViewById(R.id.frag_create_acc_email)
        etPassword = view.findViewById(R.id.frag_create_acc_password)


        btnCreateAccount = view.findViewById(R.id.frag_signup_create_account_btn)



        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference.child("Users")
        mAuth = FirebaseAuth.getInstance()



        btnCreateAccount!!.setOnClickListener { createNewAccount() }

        return view
    }

    private fun createNewAccount() {

        // Retrieving current string value of the fields edittext (USER + PASS)

        email = etEmail?.text.toString()
        password = etPassword?.text.toString()

        if (email!!.isEmpty()) {
            etEmail!!.error = "Email is required"
            etEmail!!.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail!!.error = "Please enter a valid email"
            etEmail!!.requestFocus()
            return
        }

        if (password!!.isEmpty()) {
            etPassword!!.error = "Password is required"
            etPassword!!.requestFocus()
            return

        }

        if (password!!.length < 6) {
            etPassword!!.error = "Password must be longer than 6 characters"
            etPassword!!.requestFocus()
        }



        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {

            mAuth!!
                    .createUserWithEmailAndPassword(email!!, password!!)
                    .addOnCompleteListener(this.activity!!) { task ->


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

                            // Toast.makeText(this, "Login created succesfully", Toast.LENGTH_SHORT).show()

                        } else {

                            if (!task.isSuccessful) {
                                try {
                                    throw task.exception!!

                                } catch (e: FirebaseAuthWeakPasswordException) {

                                    Timber.e("ONCOMPLETE - WEAK_PASSWORD EXCEPTION")
                                    Toast.makeText(activity, "Password is weak - please try again with a stronger password", Toast.LENGTH_SHORT).show()

                                } catch (e: FirebaseAuthUserCollisionException) {
                                    Timber.e("ONCOMPLETE - USER_ALREADY_EXSISTS EXCEPTION")
                                    Toast.makeText(activity, "A user with that email already exsists", Toast.LENGTH_SHORT).show()


                                } catch (e: FirebaseAuthInvalidCredentialsException) {
                                    Timber.e("ONCOMPLETE - MISTYPED_EMAIL EXCEPTION")
                                    Toast.makeText(activity, "Check syntax on your email, does it include a @?", Toast.LENGTH_SHORT).show()
                                }

                            }

                        }

                    }
        }


    }

    private fun verifyEmail() {
        val mUser = mAuth!!.currentUser
        mUser!!.sendEmailVerification()
                .addOnCompleteListener { task ->

                    if (task.isSuccessful) {
                        Toast.makeText(activity, "Verification email sent to " + mUser.email, Toast.LENGTH_SHORT).show()
                    } else {
                        Timber.log(3, "sendEmailVerification", task.exception)
                        Toast.makeText(activity,
                                "Failed to send verification email.",
                                Toast.LENGTH_SHORT).show()
                    }
                }

    }


    private fun updateUserInfoAndUI() {

        // Also responsible for starting new activity

        val intent = Intent(activity, MenuActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) // the FLAG_ACTIVITY_CLEAR_TOP flag clears the CreateAccountActivity from stack so that if user press back from MenuActivity, he should not be taken back to CreateAccountActivity.
        startActivity(intent)


    }
}
