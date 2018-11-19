package com.example.sulivet.sulivet.Wizard

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sulivet.sulivet.R
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.facebook.HttpMethod
import com.facebook.login.LoginManager


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


