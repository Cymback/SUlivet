package com.example.sulivet.sulivet.CoffeeActivities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.sulivet.sulivet.Activities.NutriCappuinoActivity
import com.example.sulivet.sulivet.R

class CappuccinoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cappuccino)

        val popularRoute = findViewById<View>(R.id.activity_cappuccino_popular_variants_btn)
        val nutriRoute = findViewById<View>(R.id.activity_cappuccino_nutrition_btn)

        // popularRoute.setOnClickListener { toPopular() }
        nutriRoute.setOnClickListener { toNutri() }
    }

    private fun toNutri() {
        val intent = Intent(this, NutriCappuinoActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
    }

//    private fun toPopular() {
//        val intent = Intent(this, PopuCappuActivity::class.java)
//        startActivity(intent)
//        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
    }

