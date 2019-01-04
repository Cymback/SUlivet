package com.example.sulivet.sulivet.CoffeeActivities

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.sulivet.sulivet.R

class EspressoActivity : AppCompatActivity() {

    companion object {
        const val ESPRESSOACTIVITY = "ESPRESSOACTIVITY"
    }


    fun startActivity(activity: Activity?) {

        if (activity == null || activity.isFinishing) return

        val intent = Intent(activity, EspressoActivity::class.java)
        activity.startActivity(intent)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_espresso)
    }
}
