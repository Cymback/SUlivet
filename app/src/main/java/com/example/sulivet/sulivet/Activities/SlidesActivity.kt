package com.example.sulivet.sulivet.Activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import com.example.sulivet.sulivet.MenuActivities.SettingsActivity

import com.example.sulivet.sulivet.R
import com.ramotion.paperonboarding.PaperOnboardingFragment
import com.ramotion.paperonboarding.PaperOnboardingPage
import com.ramotion.paperonboarding.listeners.PaperOnboardingOnRightOutListener

import java.util.ArrayList

class SlidesActivity : AppCompatActivity() {


    private var fragmentManager: FragmentManager? = null


    private val dataForOnboarding: ArrayList<PaperOnboardingPage>
        get() {
            val scr1 = PaperOnboardingPage("Nem Mad?!", "Hvordan gør i det? - Swipe til venstre for ren magi!",
                    Color.parseColor("#D0021B"), R.drawable.school, R.drawable.fisk)
            val scr2 = PaperOnboardingPage("RICKED", "Swipe en gang til!",
                    Color.parseColor("#D0021B"), R.drawable.fiskicon, R.drawable.fisk)


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

            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fadeout)
        }
    }
}
