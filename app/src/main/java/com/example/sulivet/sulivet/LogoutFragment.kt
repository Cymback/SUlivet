package com.example.sulivet.sulivet

import android.app.AlertDialog
import android.app.AlertDialog.Builder
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
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
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.facebook.HttpMethod
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.frag_nav_logout.*

class LogoutFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        fblogout()


        return inflater.inflate(R.layout.frag_nav_logout, container, false)


    }

    private fun fblogout() {

        if (AccessToken.getCurrentAccessToken() != null) {
            GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, GraphRequest.Callback {
                AccessToken.setCurrentAccessToken(null)
                LoginManager.getInstance().logOut()

                //finishIt()
            }).executeAsync()
        }
    }
}


