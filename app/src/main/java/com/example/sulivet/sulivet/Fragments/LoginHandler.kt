package com.example.sulivet.sulivet.Fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import com.example.sulivet.sulivet.R

class LoginHandler : AppCompatActivity() {

    fun startActivity(activity: Activity?) {

        if (activity == null || activity.isFinishing) return
        val intent = Intent(activity, LoginHandler::class.java)
        activity.startActivity(intent)
    }


    companion object {

        private const val TAG = "LoginHandler"
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
        adapter.addFragment(SignUpFragment(), "Sign up")
        adapter.addFragment(LoginFragment(), "Login")

        viewPager.adapter = adapter

    }

    override fun onBackPressed() {
//        val intent = Intent(this, MenuActivity::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) // the FLAG_ACTIVITY_CLEAR_TOP flag clears the CreateAccountActivity from stack so that if user press back from MenuActivity, he should not be taken back to CreateAccountActivity.
//        startActivity(intent)
//        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
}

