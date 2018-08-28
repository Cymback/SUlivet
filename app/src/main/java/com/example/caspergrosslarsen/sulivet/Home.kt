package com.example.caspergrosslarsen.sulivet

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.accessibility.AccessibilityManager
import android.widget.Button

import com.google.firebase.auth.FirebaseAuth

class Home : AppCompatActivity() {

    lateinit var button: Button
    lateinit var mAuth: FirebaseAuth
    lateinit var mAuthListener: FirebaseAuth.AuthStateListener

    override fun onStart() {
        super.onStart()
        mAuth.addAuthStateListener(mAuthListener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        button = findViewById<View>(R.id.logout) as Button
        mAuth = FirebaseAuth.getInstance()


        // If firebase cant find user, redirect user to MainActivity (Login-screen)
        mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            if (firebaseAuth.currentUser == null) {
                startActivity(Intent(this@Home, MainActivity::class.java))
            }
        }


        button.setOnClickListener { mAuth.signOut() }
    }
}
