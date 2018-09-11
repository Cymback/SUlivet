package com.example.caspergrosslarsen.sulivet.Activities

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ScrollView
import com.example.caspergrosslarsen.sulivet.R

class EssentialActivity : AppCompatActivity() {

    companion object {
        fun startActivity(activity: Activity?) {

            if (activity == null || activity.isFinishing) return

            val intent = Intent(activity, EssentialActivity::class.java)
            activity.startActivity(intent)

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_essential)

        initialise()


    }

    private fun initialise() {

        val myscrollView = findViewById<View>(R.id.essential_scrollview_id) as ScrollView

    }
}
