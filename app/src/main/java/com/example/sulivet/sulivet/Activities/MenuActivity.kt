package com.example.sulivet.sulivet.Activities

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.CardView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.example.sulivet.sulivet.Fragments.LoginHandler
import com.example.sulivet.sulivet.MenuActivities.*
import com.example.sulivet.sulivet.R
import com.example.sulivet.sulivet.R.menu.menu_menu
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.item_menu.view.*

class MenuActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null


    companion object {

        const val MYPROFILE = "MYPROFILE"
        const val RECIPES = "RECIPES"
        const val KITCHENESSENTIALS = "KITCHENESSENTIALS"
        const val SETTINGSPAGE = "SETTINGSPAGE"
        const val FOODPLANNER = "FOODPLANNER"
        const val CHALLENGEMODE = "CHALLENGEMODE"


        fun startActivity(activity: Activity?) {

            if (activity == null || activity.isFinishing) return

            val intent = Intent(activity, MenuActivity::class.java)
            activity.startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val myProfileRoute = findViewById<View>(R.id.menu_card_myprofile)
        val myRecipesRoute = findViewById<View>(R.id.menu_card_recipes)
        val kitchenEssentials = findViewById<View>(R.id.menu_card_kitchen_essentials)
        val settingsPage = findViewById<View>(R.id.menu_card_settings)
        val foodPlanner = findViewById<View>(R.id.menu_card_food_planner)
        val challengeMode = findViewById<View>(R.id.menu_card_challenge_mode)

        myProfileRoute.setOnClickListener { toProfilePage() }
        myRecipesRoute.setOnClickListener { toRecipesPage() }
        kitchenEssentials.setOnClickListener { toKitchenEssentials() }
        settingsPage.setOnClickListener { toSettingsPage() }
        foodPlanner.setOnClickListener { toFoodPlanner() }
        challengeMode.setOnClickListener { toChallengeMode() }


    }

    private fun toFoodPlanner() {
        val intent = Intent(this, FoodPlannerActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    private fun toSettingsPage() {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    private fun toKitchenEssentials() {
        val intent = Intent(this, KitchenEssentialsActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    private fun toChallengeMode() {
        val intent = Intent(this, ChallengeModeActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    private fun toRecipesPage() {
        val intent = Intent(this, RecipesActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    private fun toProfilePage() {

        val mAuth = FirebaseAuth.getInstance()

        if (mAuth.currentUser != null) {
            Toast.makeText(applicationContext, "You are already logged in succesfully", Toast.LENGTH_SHORT).show()

        } else {
            if (mAuth.currentUser == null) {
                val intent = Intent(this, LoginHandler::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.fade_in, R.anim.slide_out_right)

            }
        }
    }

    private fun Finish() {

        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }


    override fun onBackPressed() {

        Toast.makeText(applicationContext, "Back press disabled!", Toast.LENGTH_SHORT).show()
    }

}

