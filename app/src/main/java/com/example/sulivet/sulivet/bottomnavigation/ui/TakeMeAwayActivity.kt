package com.example.sulivet.sulivet.bottomnavigation.ui

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.example.sulivet.sulivet.bottomnavigation.helper.BottomNavigationPosition
import com.example.sulivet.sulivet.R
import com.example.sulivet.sulivet.bottomnavigation.extension.active
import com.example.sulivet.sulivet.bottomnavigation.extension.attach
import com.example.sulivet.sulivet.bottomnavigation.extension.detach
import com.example.sulivet.sulivet.bottomnavigation.extension.disableShiftMode
import com.example.sulivet.sulivet.bottomnavigation.helper.createFragment
import com.example.sulivet.sulivet.bottomnavigation.helper.findNavigationPositionById
import com.example.sulivet.sulivet.bottomnavigation.helper.getTag

class TakeMeAwayActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    fun startActivity(activity: Activity?) {

        if (activity == null || activity.isFinishing) return

        val intent = Intent(activity, TakeMeAwayActivity::class.java)
        activity.startActivity(intent)
    }


    private val KEY_POSITION = "keyPosition"

    private var navPosition: BottomNavigationPosition = BottomNavigationPosition.HOME

    private lateinit var toolbar: Toolbar

    private lateinit var bottomNavigation: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restoreSaveInstanceState(savedInstanceState)
        setContentView(R.layout.activity_take_me_away)
        toolbar = findViewById(R.id.toolbar)
        bottomNavigation = findViewById(R.id.bottom_navigation)
        setSupportActionBar(toolbar)
        initBottomNavigation()
        initFragment(savedInstanceState)
    }

    override fun onBackPressed() {

    }

    override fun onSaveInstanceState(outState: Bundle?) {
        // Store the current navigation position.
        outState?.putInt(KEY_POSITION, navPosition.id)
        super.onSaveInstanceState(outState)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        navPosition = findNavigationPositionById(item.itemId)
        return switchFragment(navPosition)
    }

    private fun restoreSaveInstanceState(savedInstanceState: Bundle?) {
        // Restore the current navigation position.
        savedInstanceState?.also {
            val id = it.getInt(KEY_POSITION, BottomNavigationPosition.HOME.id)
            navPosition = findNavigationPositionById(id)
        }
    }

    private fun initBottomNavigation() {
        bottomNavigation.disableShiftMode() // Extension function
        bottomNavigation.active(navPosition.position)   // Extension function
        bottomNavigation.setOnNavigationItemSelectedListener(this)
    }

    private fun initFragment(savedInstanceState: Bundle?) {
        savedInstanceState ?: switchFragment(BottomNavigationPosition.HOME)
    }

    /**
     * Immediately execute transactions with FragmentManager#executePendingTransactions.
     */
    private fun switchFragment(navPosition: BottomNavigationPosition): Boolean {
        return supportFragmentManager.findFragment(navPosition).let {
            if (it.isAdded) return false
            supportFragmentManager.detach() // Extension function
            supportFragmentManager.attach(it, navPosition.getTag()) // Extension function
            supportFragmentManager.executePendingTransactions()
        }
    }

    private fun FragmentManager.findFragment(position: BottomNavigationPosition): Fragment {
        return findFragmentByTag(position.getTag()) ?: position.createFragment()
    }

}