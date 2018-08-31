package com.example.caspergrosslarsen.sulivet

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class StartTwoActivity : AppCompatActivity() {

    lateinit var button: Button
    lateinit var sudata: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_en_hurtig_sjover_to)


        sudata = findViewById(R.id.sudata)
        sudata.apply {
            text = intent.getStringExtra(StartOneActivity.EXTRASEEKARC)

        }


    }

}
