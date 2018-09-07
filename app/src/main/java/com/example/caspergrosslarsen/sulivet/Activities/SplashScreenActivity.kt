package com.example.caspergrosslarsen.sulivet.Activities

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import gr.net.maroulis.library.EasySplashScreen

import com.example.caspergrosslarsen.sulivet.R

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val config = EasySplashScreen(this@SplashScreenActivity)
                .withFullScreen()
                .withTargetActivity(MenuActivity::class.java)
                .withSplashTimeOut(5000)
                .withBackgroundColor(Color.parseColor("#ffffff"))
                .withLogo(R.drawable.sulivet)
                .withHeaderText("")
                .withFooterText("Copyright 2018 - CPH Business")
                .withBeforeLogoText("")
                .withAfterLogoText("")


        // SET TEXT COLOR


        config.headerTextView.setTextColor(Color.BLACK)
        config.footerTextView.setTextColor(Color.BLACK)
        config.afterLogoTextView.setTextColor(Color.BLACK)
        config.beforeLogoTextView.setTextColor(Color.BLACK)

        // SET TO VIEW

        val view = config.create()

        // SET VIEW TO CONTENT VIEW

        setContentView(view)


    }
}