package com.example.caspergrosslarsen.sulivet

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.triggertrap.seekarc.SeekArc
import com.triggertrap.seekarc.SeekArc.OnSeekArcChangeListener
import timber.log.Timber


class WizardOne : AppCompatActivity() {

    lateinit var mSeekArc: SeekArc
    lateinit var mSeekArcProgress: TextView


    companion object {
        val EXTRASEEKARC = "EXTRASEEKARC" // key identifier for toWizardTwo intent

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wizard_one)

        mSeekArc = findViewById(R.id.seekArc)
        mSeekArcProgress = findViewById(R.id.seekArcProgress)


        mSeekArc.setOnSeekArcChangeListener(object : OnSeekArcChangeListener {
            override fun onProgressChanged(p0: SeekArc?, progress: Int, p2: Boolean) {

                // TODO: MAKE SEEKARC RED, from 0-500 - 500-100 = YELLOW, 1000-1500 = GREEN
                mSeekArcProgress.text = progress.toString()

            }

            override fun onStartTrackingTouch(p0: SeekArc?) {
                Timber.d("Start tracking")


            }

            override fun onStopTrackingTouch(p0: SeekArc?) {
                Timber.d("Stop tracking")

            }


        })


    }

    fun toWizardTwo(view: View) {


        var myIntProgress: Int = mSeekArcProgress.text.toString().toInt()

        if (myIntProgress < 1200 && myIntProgress > 800) {
            Toast.makeText(this, "Korrekt, gennemsnittet ligger mellem 800 og 1200kr", Toast.LENGTH_LONG).show()

            val intent = Intent(this@WizardOne, WizardTwo::class.java)
            intent.apply {
                this.putExtra(EXTRASEEKARC, mSeekArcProgress.text)
            }
            startActivity(intent)


        } else {
            (myIntProgress < 800)
            Toast.makeText(this, "Det er muligt, men lidt under gennemsnittet, skal vi ikke prøve at sætte det lidt højere?", Toast.LENGTH_SHORT).show()
        }

        // TODO: CREATE MORE IF STATEMENTS, below 200 (insert toast), above 1200, muligt men kræver arbejde ved siden af
    }


}
