package com.example.sulivet.sulivet

import android.app.AlertDialog
import android.app.AlertDialog.Builder
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.text.Layout
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

class LogoutFragment : DialogFragment() {


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

        var builder: AlertDialog.Builder = AlertDialog.Builder(context, 3)
        var inflater: LayoutInflater = layoutInflater
        var view: View = inflater.inflate(R.layout.dialog_pls_sign_up_box, null)

        builder.setView(view)
        builder.setNegativeButton("No"
        ) { dialog, which ->

            Toast.makeText(activity, "sorry i asked", Toast.LENGTH_SHORT).show()
            dialog.dismiss()

        }

        builder.setPositiveButton("Yes"
        ) { dialog, which ->
            dialog.dismiss()
            val intent = Intent(activity, LoginHandler::class.java)
            startActivity(intent)
        }

        var dialog: Dialog = builder.create()
        dialog.show()
    }

    private fun areUSureBox() {

        var builder: AlertDialog.Builder = AlertDialog.Builder(context, 4)
        var inflater: LayoutInflater = layoutInflater
        var view: View = inflater.inflate(R.layout.dialog_are_you_sure_box, null)

        builder.setView(view)
        builder.setNegativeButton("No"
        ) { dialog, which ->
            dialog.dismiss()

        }

        builder.setPositiveButton("Yes"
        ) { dialog, which ->
            dialog.dismiss()
            val intent = Intent(activity, MenuActivity::class.java)
            startActivity(intent)
        }

        var dialog: Dialog = builder.create()
        dialog.show()


    }


}