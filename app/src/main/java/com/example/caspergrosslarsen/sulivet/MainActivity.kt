package com.example.caspergrosslarsen.sulivet

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast

import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class MainActivity : AppCompatActivity() {


    lateinit var button: SignInButton
    lateinit var mAuth: FirebaseAuth

    lateinit var mGoogleSignInClient: GoogleApiClient
    lateinit var mAuthListener: FirebaseAuth.AuthStateListener


    override fun onStart() {
        super.onStart()

        mAuth.addAuthStateListener(mAuthListener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById<View>(R.id.googleBtn) as SignInButton
        mAuth = FirebaseAuth.getInstance()

        button.setOnClickListener { signIn() }

        mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            if (firebaseAuth.currentUser != null) {
                startActivity(Intent(this@MainActivity, Home::class.java))
            }
        }


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        mGoogleSignInClient = GoogleApiClient.Builder(this)
                .enableAutoManage(this) { Toast.makeText(this@MainActivity, "wrong something", Toast.LENGTH_SHORT).show() }
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()
    }


    private fun signIn() {
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleSignInClient)
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account)
            } catch (e: ApiException) {
                e.printStackTrace()
                // Google Sign In failed, update UI appropriately
                Toast.makeText(this@MainActivity, "wrong something", Toast.LENGTH_SHORT).show()
                // ...
            }

        }
    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {

        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("TAG", "signInWithCredential:success")
                        val user = mAuth.currentUser
                        // updateUI(user);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("TAG", "signInWithCredential:failure", task.exception)
                        Toast.makeText(this@MainActivity, "Authentication failed.", Toast.LENGTH_SHORT).show()
                        // updateUI(null);
                    }

                    // ...
                }
    }

    companion object {
        private val RC_SIGN_IN = 2
    }
}