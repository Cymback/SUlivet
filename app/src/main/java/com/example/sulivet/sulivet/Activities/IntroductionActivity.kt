package com.example.sulivet.sulivet.Activities

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.example.sulivet.sulivet.Fragments.LoginHandler
import com.example.sulivet.sulivet.R

class IntroductionActivity : AppCompatActivity() {

    lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introduction)


        val imageView = findViewById<View>(R.id.intro_btn_letsgo)

        imageView.setOnClickListener { btnToMenu() }
    }

    private fun btnToMenu() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

    }
}
