package com.example.sulivet.sulivet.Activities

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.sulivet.sulivet.R

class ExpensiveRecipesActivity : AppCompatActivity() {

    companion object {
        fun startActivity(activity: Activity?) {

            if (activity == null || activity.isFinishing) return

            val intent = Intent(activity, ExpensiveRecipesActivity::class.java)

            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expensive_recipes)
    }
}
