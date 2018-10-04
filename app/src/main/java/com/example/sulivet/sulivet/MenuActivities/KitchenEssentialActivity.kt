package com.example.sulivet.sulivet.MenuActivities

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.sulivet.sulivet.Activities.MenuActivity
import com.example.sulivet.sulivet.Adapters.EssentialAdapter
import com.example.sulivet.sulivet.Model.Essential
import com.example.sulivet.sulivet.R

class KitchenEssentialActivity : AppCompatActivity() {

    companion object {
        fun startActivity(activity: Activity?) {

            if (activity == null || activity.isFinishing) return

            val intent = Intent(activity, KitchenEssentialActivity::class.java)
            activity.startActivity(intent)

        }

    }

    private lateinit var lstEssential: MutableList<Essential>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_essential)



        lstEssential = ArrayList()

        lstEssential.add(Essential( "10 U-undværlige ting til studenten", R.drawable.fisk))
        lstEssential.add(Essential("Hjælp til det huslige?", R.drawable.fisk))


        val essview = findViewById<View>(R.id.EssentialAdapterView) as RecyclerView
        val myAdapter = EssentialAdapter(this, lstEssential)
        essview.layoutManager = GridLayoutManager(this, 1)
        essview.adapter = myAdapter

    }

    override fun onBackPressed() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
    }
}
