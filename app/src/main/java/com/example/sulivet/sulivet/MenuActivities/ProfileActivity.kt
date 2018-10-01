package com.example.sulivet.sulivet.MenuActivities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.sulivet.sulivet.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_profile.*
import timber.log.Timber

class ProfileActivity : AppCompatActivity() {

    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null

    private var name: TextView? = null
    private var email: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


        initialise()


    }

    private fun initialise() {

        val user = FirebaseAuth.getInstance().currentUser

        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference.child("Users")

        mDatabaseReference!!.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Timber.log(10, "DATABASE ERROR - CHECK LOG")
            }

            override fun onDataChange(snapshot: DataSnapshot) {

                if (user != null) {

                    updateUI()
                }
            }

            private fun updateUI() {


                profile__email_view.text = getString(R.string.profile_user_email_view) + user!!.email
                profile_username_view.text = getString(R.string.profile_show_username) + user!!.displayName

            }

        })


        name = findViewById<View>(R.id.profile_username_view) as TextView
        email = findViewById<View>(R.id.profile__email_view) as TextView


    }
}
