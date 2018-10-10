package com.example.sulivet.sulivet.Activities

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.sulivet.sulivet.*
import com.example.sulivet.sulivet.Fragments.LoginHandler
import com.example.sulivet.sulivet.MenuActivities.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MenuActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var mAuth: FirebaseAuth? = null
    private lateinit var toggle: ActionBarDrawerToggle


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

        setSupportActionBar(toolbar)


        toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()


        nav_view.setNavigationItemSelectedListener(this)


        val myProfileRoute = findViewById<View>(R.id.menu_card_myprofile)
        val myRecipesRoute = findViewById<View>(R.id.menu_card_recipes)
        val kitchenEssentials = findViewById<View>(R.id.menu_card_kitchen_essentials)
        val settingsPage = findViewById<View>(R.id.menu_card_settings)
        val foodPlanner = findViewById<View>(R.id.menu_card_food_planner)
        val challengeMode = findViewById<View>(R.id.menu_card_challenge_mode)

        myProfileRoute.setOnClickListener { toProfilePage() }
        myRecipesRoute.setOnClickListener { toRecipesPage() }
        kitchenEssentials.setOnClickListener { KitchenEssentialActivity.startActivity(this) }
        settingsPage.setOnClickListener { toFindFoodActivity() }
        foodPlanner.setOnClickListener { toFoodPlanner() }
        challengeMode.setOnClickListener { toChallengeMode() }


    }


    private fun toFoodPlanner() {

        val mAuth = FirebaseAuth.getInstance()

        mAuth.signOut()

//        val intent = Intent(this, FoodPlannerActivity::class.java)
//        startActivity(intent)
//        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    private fun toFindFoodActivity() {
        val intent = Intent(this, FoodFinderActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    private fun toChallengeMode() {

        val mAuth = FirebaseAuth.getInstance()

        if (mAuth.currentUser == null) {

            // Grey out challenge mode and tell its not available

        } else {

            val intent = Intent(this, ChallengeModeActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }
    }

    private fun toRecipesPage() {
        val intent = Intent(this, RecipesActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    private fun toProfilePage() {

        val mAuth = FirebaseAuth.getInstance()

        if (mAuth.currentUser != null) {
            Toast.makeText(applicationContext, "You are already logged in", Toast.LENGTH_SHORT).show()

        } else {
            if (mAuth.currentUser == null) {
                val intent = Intent(this, LoginHandler::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_left, R.anim.fadeout)

            }
        }
    }

    private fun finishIt() {

        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.fadeout)
    }


    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            return
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.main, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        when (item.itemId) {
//            R.id.action_settings -> return true
//            else -> return super.onOptionsItemSelected(item)
//        }
//    }

    private fun displayScreen(id: Int) {

        var fragmentTag = ""
        var fragment: Fragment? = null

        when (id) {

            R.id.nav_home -> {
                MenuActivity
            }

            R.id.nav_myprofile -> {
                fragment = MyProfileFragment.newInstance()
                fragmentTag = MyProfileFragment.TAG

            }

            R.id.nav_my_food -> {
                fragment = MyFoodFragment.newInstance()
                fragmentTag = MyFoodFragment.TAG

            }

            R.id.nav_settings -> {
                fragment = SettingsFragment.newInstance()
                fragmentTag = SettingsFragment.TAG

            }

            R.id.nav_logout -> {

                if (FirebaseAuth.getInstance().currentUser != null) {

                    areUSureBox()

                } else {

                    if (FirebaseAuth.getInstance().currentUser == null)

                        plsSignUpBox()
                }

                LogoutFragment()

            }
        }

        if (fragment != null) {

            supportFragmentManager.beginTransaction().replace(R.id.sldajfksdajmsdf, fragment, fragmentTag).commit()
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.

        displayScreen(item.itemId)


        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


    private fun plsSignUpBox() {

        var builder: AlertDialog.Builder = AlertDialog.Builder(this, R.style.AppTheme)
        var inflater: LayoutInflater = layoutInflater
        var view: View = inflater.inflate(R.layout.dialog_pls_sign_up_box, null)

        builder.setView(view)
        builder.setNegativeButton("No"
        ) { dialog, which ->

            Toast.makeText(this, "sorry i asked", Toast.LENGTH_SHORT).show()
            dialog.dismiss()

        }

        builder.setPositiveButton("Yes"
        ) { dialog, which ->
            dialog.dismiss()
            val intent = Intent(this, LoginHandler::class.java)
            startActivity(intent)
        }

        var dialog: Dialog = builder.create()
        dialog.show()

    }

    private fun areUSureBox() {
        var builder: AlertDialog.Builder = AlertDialog.Builder(this, R.style.AppTheme)
        var inflater: LayoutInflater = layoutInflater
        var view: View = inflater.inflate(R.layout.dialog_are_you_sure_box, null)

        builder.setView(view)
        builder.setNegativeButton("No"
        ) { dialog, which ->
            dialog.dismiss()

        }

        builder.setPositiveButton("Yes"
        ) { dialog, which ->
            dialog.dismiss()
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        var dialog: Dialog = builder.create()
        dialog.show()

    }

}

