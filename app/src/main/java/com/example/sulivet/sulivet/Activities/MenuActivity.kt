package com.example.sulivet.sulivet.Activities

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.PorterDuff
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.example.sulivet.sulivet.*
import com.example.sulivet.sulivet.Fragments.LoginHandler
import com.example.sulivet.sulivet.MenuActivities.*
import com.example.sulivet.sulivet.R.id.drawer_layout
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.facebook.HttpMethod
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MenuActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var mAuth: FirebaseAuth? = null
    private lateinit var toggle: ActionBarDrawerToggle

    private var progressBar: ProgressBar? = null


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

        progressBar = findViewById(R.id.menu_logout_drawer_id)



        setSupportActionBar(toolbar)


        toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()


        nav_view.setNavigationItemSelectedListener(this)


        val myProfileRoute = findViewById<View>(R.id.menu_card_myprofile)
        val myRecipesRoute = findViewById<View>(R.id.menu_card_recipes)
        val kitchenEssentials = findViewById<View>(R.id.menu_card_kitchen_essentials)
        val foodPlanner = findViewById<View>(R.id.menu_card_food_planner)

        myProfileRoute.setOnClickListener { toProfilePage() }
        myRecipesRoute.setOnClickListener { toRecipesPage() }
        kitchenEssentials.setOnClickListener { KitchenEssentialActivity.startActivity(this) }
        foodPlanner.setOnClickListener { toFoodPlanner() }


    }


    private fun toFoodPlanner() {

        val mAuth = FirebaseAuth.getInstance()

        mAuth.signOut()

//        val intent = Intent(this, FoodPlannerActivity::class.java)
//        startActivity(intent)
//        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
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


    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            return
        }
    }

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


                fbUserLogout()
                userLogout()


            }
        }

        if (fragment != null) {

            supportFragmentManager.beginTransaction().replace(R.id.sldajfksdajmsdf, fragment, fragmentTag).commit()
        }

    }

    private fun fbUserLogout() {


        val accessToken = AccessToken.getCurrentAccessToken()

        if (accessToken != null) {


            progressBar!!.indeterminateDrawable.setColorFilter(ContextCompat.getColor(this, R.color.greysigninsignup), PorterDuff.Mode.SRC_IN)
            progressBar!!.visibility = View.VISIBLE

            GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, GraphRequest.Callback {
                AccessToken.setCurrentAccessToken(null)
                LoginManager.getInstance().logOut()
                toFront()

                progressBar!!.visibility = View.GONE

                finish()
            }).executeAsync()
        }
    }

    private fun toFront() {
        val intent = Intent(this, LoginHandler::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.fadeout)
    }

    private fun userLogout() {

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.

        displayScreen(item.itemId)


        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

}

