package com.example.caspergrosslarsen.sulivet

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class WizardTwo : AppCompatActivity() {

    lateinit var button: Button
    lateinit var sudata: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wizard_two)


        sudata = findViewById(R.id.sudata)
        sudata.apply {
            text = intent.getStringExtra(WizardOne.EXTRASEEKARC)

        }


    }

}
