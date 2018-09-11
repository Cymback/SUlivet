package com.example.caspergrosslarsen.sulivet.Activities

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.caspergrosslarsen.sulivet.R

class FoodGeneratorActivity : AppCompatActivity() {

    companion object {
        fun startActivity(activity: Activity?) {

            if (activity == null || activity.isFinishing) return

            val intent = Intent(activity, FoodGeneratorActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_generator)
    }
}
