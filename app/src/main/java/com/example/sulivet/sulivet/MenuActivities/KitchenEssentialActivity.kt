package com.example.sulivet.sulivet.MenuActivities

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.sulivet.sulivet.Activities.MenuActivity
import com.example.sulivet.sulivet.Adapters.EssentialAdapter
import com.example.sulivet.sulivet.Model.Essential
import com.example.sulivet.sulivet.R
import kotlinx.android.synthetic.main.activity_essential.*

class KitchenEssentialActivity : AppCompatActivity() {

    companion object {
        fun startActivity(activity: Activity?) {

            if (activity == null || activity.isFinishing) return

            val intent = Intent(activity, KitchenEssentialActivity::class.java)
            activity.startActivity(intent)

            activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)

        }

    }

    private lateinit var lstEssential: MutableList<Essential>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_essential)

        val itemAnimator = DefaultItemAnimator()

        itemAnimator.addDuration = 1000
        // itemAnimator.removeDuration = 1000
        EssentialAdapterView.itemAnimator


        lstEssential = ArrayList()

        lstEssential.add(Essential("10 tricks for students", R.drawable.bba))
        lstEssential.add(Essential("Equipment Essentials", R.drawable.bba))
        lstEssential.add(Essential("Equipment Essentials", R.drawable.bba))
        lstEssential.add(Essential("Equipment Essentials", R.drawable.bba))


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
