package com.example.sulivet.sulivet.Activities

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import com.example.sulivet.sulivet.Fragments.LoginHandler
import com.example.sulivet.sulivet.R
import com.example.sulivet.sulivet.bottomnavigation.ui.TakeMeAwayActivity
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.facebook.HttpMethod

class OnBoardingActivity : AppCompatActivity() {


    private var progressBar: ProgressBar? = null

    private var btnToLogin: Button? = null
    private var btnToRegister: Button? = null
    private var btnFacebook: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_on_boarding)

        progressBar = findViewById(R.id.onboarding_progress_bar)


        btnFacebook = findViewById(R.id.onboarding_facebook_btn)
        btnToLogin = findViewById(R.id.onboarding_login_btn)
        btnToRegister = findViewById(R.id.onboarding_register_btn)


        btnToRegister!!.setOnClickListener { handleLogin() }
        btnFacebook!!.setOnClickListener { facebookLogin() }
        btnToLogin!!.setOnClickListener { handleLogin() }
    }

    private fun handleLogin() {
        val intent = Intent(this, LoginHandler::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.fadeout)

    }

    private fun facebookLogin() {


        val accessToken = AccessToken.getCurrentAccessToken()

        if (accessToken != null) {
            val intent = Intent(this, TakeMeAwayActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.fadeout)


            progressBar!!.indeterminateDrawable.setColorFilter(ContextCompat.getColor(this, R.color.brown_main), PorterDuff.Mode.SRC_IN)
            progressBar!!.visibility = View.VISIBLE

            GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, GraphRequest.Callback {
                AccessToken.setCurrentAccessToken(null)
                // LoginManager.getInstance().logOut()

                progressBar!!.visibility = View.GONE

                finish()
            }).executeAsync()

        } else {


        }

    }

}
