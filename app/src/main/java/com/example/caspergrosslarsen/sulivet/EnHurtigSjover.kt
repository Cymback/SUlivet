package com.example.caspergrosslarsen.sulivet

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import com.triggertrap.seekarc.SeekArc
import com.triggertrap.seekarc.SeekArc.OnSeekArcChangeListener


class EnHurtigSjover : AppCompatActivity() {

    lateinit var mSeekArc: SeekArc
    lateinit var mSeekArcProgress: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_en_hurtig_sjover)

        mSeekArc = findViewById(R.id.seekArc)
        mSeekArcProgress = findViewById(R.id.seekArcProgress)


        mSeekArc.setOnSeekArcChangeListener(object : OnSeekArcChangeListener {
            override fun onProgressChanged(p0: SeekArc?, progress: Int, p2: Boolean) {
                mSeekArcProgress.text = progress.toString()

            }

            override fun onStartTrackingTouch(p0: SeekArc?) {

            }

            override fun onStopTrackingTouch(p0: SeekArc?) {

            }


        })


    }
}
