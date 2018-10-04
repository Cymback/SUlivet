package com.example.sulivet.sulivet.Fragments

import android.content.Intent
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity

import android.support.v4.view.ViewPager
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.example.sulivet.sulivet.Activities.MenuActivity
import com.example.sulivet.sulivet.R

class LoginHandler : AppCompatActivity() {

    companion object {

        private val TAG = "LoginHandler"
    }

    private var mSectionsPageAdapter: SectionsPageAdapter? = null

    private var mViewPager: ViewPager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_handler)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        mSectionsPageAdapter = SectionsPageAdapter(supportFragmentManager)

        mViewPager = findViewById(R.id.container)
        setupViewPager(mViewPager!!)

        val tabLayout = findViewById<View>(R.id.tabs) as TabLayout
        tabLayout.setupWithViewPager(mViewPager)


    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = SectionsPageAdapter(supportFragmentManager)
        adapter.addFragment(LoginFragment(), "Login")
        adapter.addFragment(SignUpFragment(), "Sign up")

        viewPager.adapter = adapter

    }

    override fun onBackPressed() {
        val intent = Intent(this, MenuActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) // the FLAG_ACTIVITY_CLEAR_TOP flag clears the CreateAccountActivity from stack so that if user press back from MenuActivity, he should not be taken back to CreateAccountActivity.
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
}