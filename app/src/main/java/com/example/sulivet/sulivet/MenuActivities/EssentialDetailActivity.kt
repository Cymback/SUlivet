package com.example.sulivet.sulivet.MenuActivities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.sulivet.sulivet.R

class EssentialDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_HEADER = "HEADER"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_essential_detail)
    }
}
