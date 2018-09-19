package com.example.sulivet.sulivet.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.sulivet.sulivet.Model.User
import com.example.sulivet.sulivet.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_profile.*
import timber.log.Timber

class ProfileActivity : AppCompatActivity() {

    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null

    private var Name: TextView? = null
    private var Email: TextView? = null
    private var Phone: TextView? = null
    private var Interests: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


        initialise()


    }

    private fun initialise() {

        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference.child("Users")

        mDatabaseReference!!.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Timber.log(10, "DATABASE ERROR - CHECK LOG")
            }

            override fun onDataChange(snapshot: DataSnapshot) {

                val user = snapshot.getValue(User::class.java)

                if (user == null) {
                    Timber.log(20, "User data is NULL")
                    Toast.makeText(this@ProfileActivity, "USER DATA IS NULL", Toast.LENGTH_SHORT).show()
                    return
                }

                // profile_username.text = user!!.name
                // profile__email_view.text = user!!.email

            }

        })


        Name = findViewById<View>(R.id.profile_username) as TextView
        Email = findViewById<View>(R.id.profile__email_view) as TextView
        Phone = findViewById<View>(R.id.profile_telephone_view) as TextView
        Interests = findViewById<View>(R.id.profile_interests_view) as TextView


    }
}
