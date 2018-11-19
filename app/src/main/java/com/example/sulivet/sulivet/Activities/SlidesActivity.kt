package com.example.sulivet.sulivet.Activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.example.sulivet.sulivet.Fragments.LoginHandler
import com.example.sulivet.sulivet.R
import com.example.sulivet.sulivet.bottomnavigation.ui.TakeMeAwayActivity
import com.facebook.AccessToken
import com.ramotion.paperonboarding.PaperOnboardingFragment
import com.ramotion.paperonboarding.PaperOnboardingPage
import java.util.*

class SlidesActivity : AppCompatActivity() {


    private var fragmentManager: FragmentManager? = null


    private val dataForOnboarding: ArrayList<PaperOnboardingPage>
        get() {
            val scr1 = PaperOnboardingPage("Estimate your workout needs", "Every sort of coffee has different calories",
                    Color.parseColor("#522D2D"), R.drawable.burncalories, R.mipmap.cappuccinoicon)
            val scr2 = PaperOnboardingPage("Find your favorite coffee place!", "53w5",
                    Color.parseColor("#522D2D"), R.drawable.googlemaps, R.mipmap.cappuccinoicon)


            val elements = ArrayList<PaperOnboardingPage>()
            elements.add(scr1)
            elements.add(scr2)
            return elements
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_activity)

        fragmentManager = supportFragmentManager

        val onBoardingFragment = PaperOnboardingFragment.newInstance(dataForOnboarding)

        val fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.add(R.id.fragment_container, onBoardingFragment)
        fragmentTransaction.commit()

        onBoardingFragment.setOnRightOutListener {

            isUserLoggedIn()

        }
    }

    private fun isUserLoggedIn() {
        val accessToken = AccessToken.getCurrentAccessToken()

        if (accessToken != null) {
            val intent = Intent(this, TakeMeAwayActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_in)
        } else {

            val intent = Intent(this, LoginHandler::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_in)


        }
    }
}
