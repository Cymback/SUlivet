package com.example.sulivet.sulivet

import android.app.AlertDialog
import android.app.AlertDialog.Builder
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.sulivet.sulivet.Activities.MenuActivity
import com.example.sulivet.sulivet.Fragments.LoginHandler
import com.example.sulivet.sulivet.Fragments.SignUpFragment
import com.example.sulivet.sulivet.R.drawable.bba
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.frag_nav_logout.*

class LogoutFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        didUserSignOut()

        return inflater.inflate(R.layout.frag_nav_logout, null)


    }
    private fun didUserSignOut() {

        if (FirebaseAuth.getInstance().currentUser != null) {

            areUSureBox()

        } else {

            if (FirebaseAuth.getInstance().currentUser == null)

                plsSignUpBox()
        }
    }

    private fun plsSignUpBox() {

        val builder = AlertDialog.Builder(context, 2)

        builder.setTitle("Ya'll sure you dont want to create an account?")
        builder.setMessage("Press yes")
        // root_layout.setBackgroundColor(0x0000FF00)

        builder.setPositiveButton("YES"
        ) { dialog, which ->
            val intent = Intent(activity, LoginHandler::class.java)
            startActivity(intent)

        }

        builder.setNegativeButton("NO"
        ) { dialog, which ->
            val intent = Intent(activity, MenuActivity::class.java)
            startActivity(intent)
            Toast.makeText(activity, "ok sorry i asked", Toast.LENGTH_SHORT).show()
        }

        val dialog: AlertDialog = builder.create()

        dialog.show()
    }

    private fun areUSureBox() {

        val builder = AlertDialog.Builder(context, 1)

        builder.setTitle("You sure bro?")
        builder.setMessage("Så tryk på ja... spasser")
        //  root_layout.setBackgroundColor(0x0000FF00)

        builder.setPositiveButton("YES"
        ) { dialog, which ->

            FirebaseAuth.getInstance().signOut()
            val intent = Intent(activity, MenuActivity::class.java)
            (Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)

            Toast.makeText(activity, "Logged out succesfully", Toast.LENGTH_SHORT).show()

        }

        builder.setNegativeButton("NO"
        ) { dialog, which ->
            val intent = Intent(activity, MenuActivity::class.java)
            startActivity(intent)
        }

        val dialog: AlertDialog = builder.create()

        dialog.show()

    }


}