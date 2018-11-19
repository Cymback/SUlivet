package com.example.sulivet.sulivet.Activities

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.sulivet.sulivet.Model.CoffeeNames
import com.example.sulivet.sulivet.R


class CustomizeDrinksActivity : AppCompatActivity() {


    companion object {
        const val EXTRA_DRINK_NAME = "TITLEDRINK"

        fun startAsdkjfsa(activity: Activity?, coffeeModel: CoffeeNames) {
            if (activity == null || activity.isFinishing) return

            val intent = Intent(activity, CustomizeDrinksActivity::class.java)
            intent.putExtra(EXTRA_DRINK_NAME, coffeeModel)
            activity.startActivity(intent)


        }
    }


    private lateinit var tvCoffeName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customize_drinks)



        tvCoffeName = findViewById(R.id.activity_customize_drink_name)


        val coffeNamess = intent.getSerializableExtra(EXTRA_DRINK_NAME) as CoffeeNames

        // Display
        tvCoffeName.text = coffeNamess.name

        Glide.with(this)
                .load(coffeNamess.image)
                .into(findViewById(R.id.activity_customize_header_img))


    }

}
