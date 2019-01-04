package com.example.sulivet.sulivet.Activities

import android.animation.ValueAnimator
import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.sulivet.sulivet.Model.CoffeeNames
import com.example.sulivet.sulivet.R
import kotlinx.android.synthetic.main.activity_customize_drinks.*


class CustomizeDrinksActivity : AppCompatActivity() {

    private var btnOrderCoffee: Button? = null


    companion object {
        const val EXTRA_DRINK_NAME = "TITLEDRINK"

        fun startAsdkjfsa(activity: Activity?, coffeeModel: CoffeeNames) {
            if (activity == null || activity.isFinishing) return

            val intent = Intent(activity, CustomizeDrinksActivity::class.java)
            intent.putExtra(EXTRA_DRINK_NAME, coffeeModel)
            activity.startActivity(intent)


        }
    }


    private lateinit var tvCoffeName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customize_drinks)

        btnOrderCoffee = findViewById(R.id.activity_customize_order_coffee_btn)

        tvCoffeName = findViewById(R.id.activity_customize_drink_name)


        val coffeNamess = intent.getSerializableExtra(EXTRA_DRINK_NAME) as CoffeeNames

        // Display
        tvCoffeName.text = coffeNamess.name

        Glide.with(this)
                .load(coffeNamess.image)
                .into(findViewById(R.id.activity_customize_header_img))

        btnOrderCoffee!!.setOnClickListener {
            av_from_code.playAnimation()
            //startCheckAnimation()
        }
    }

//    private fun startCheckAnimation() {
//            val animationView =
//            val animator = ValueAnimator.ofFloat(0f, 1f).setDuration(500)
//            animator.addUpdateListener{ valueAnimator -> animationView.setProgress(valueAnimator.animatedValue as Float) }
//        if (animationView.getProgress() === 0f)
//            {
//                animator.start()
//            }
//            else
//            {
//                animationView.setProgress(0f)
//            }
//        }
//    }

}
