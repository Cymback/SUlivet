package com.example.sulivet.sulivet.MenuActivities

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ScrollView
import com.example.sulivet.sulivet.Activities.MenuActivity
import com.example.sulivet.sulivet.R
import kotlinx.android.synthetic.main.activity_essential.*
import kotlinx.android.synthetic.main.item_essential.view.*

class KitchenEssentialActivity : AppCompatActivity() {

    companion object {
        fun startActivity(activity: Activity?) {

            if (activity == null || activity.isFinishing) return

            val intent = Intent(activity, KitchenEssentialActivity::class.java)
            activity.startActivity(intent)

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_essential)

        initialise()


        // 1
        tile1_essential.item_essential_header1.text = getString(R.string.essential_spices)
        tile1_essential.item_essential_description.text = getString(R.string.essential_most_needed_spices)
        //2
        tile2_essential.item_essential_header1.text = getString(R.string.essential_nice_to_have)
        tile2_essential.item_essential_description.text = getString(R.string.essential_list_of_cool_kitchenthings)
        //3
        tile3_essential.item_essential_header1.text = getString(R.string.essential_10_student_things)
        tile3_essential.item_essential_description.text = getString(R.string.essential_list_to_student)
        //4
        tile4_essential.item_essential_header1.text = getString(R.string.essential_just_moved_out)
        tile4_essential.item_essential_description.text = getString(R.string.essential_tools_to_complete_recipes)


    }

    private fun initialise() {

        val myscrollView = findViewById<View>(R.id.essential_scrollview_id) as ScrollView

    }


    override fun onBackPressed() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
    }
}
