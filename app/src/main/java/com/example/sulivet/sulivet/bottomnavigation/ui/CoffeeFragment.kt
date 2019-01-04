package com.example.sulivet.sulivet.bottomnavigation.ui

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sulivet.sulivet.CoffeeActivities.*

import com.example.sulivet.sulivet.R


class CoffeeFragment : Fragment() {


    private var espressoRoute: CardView? = null
    private var americanoRoute: CardView? = null
    private var irishRoute: CardView? = null
    private var lateRoute: CardView? = null
    private var mochaRoute: CardView? = null
    private var cappRoute: CardView? = null

    companion object {
        val TAG: String = CoffeeFragment::class.java.simpleName
        fun newInstance() = CoffeeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.title = getString(R.string.title_coffee)
        val view = inflater.inflate(R.layout.fragment_coffee, container, false)



        espressoRoute = view.findViewById(R.id.navi_bot_espresso)
        americanoRoute = view.findViewById(R.id.navi_bot_americano)
        irishRoute = view.findViewById(R.id.navi_bot_irish_coffee)
        lateRoute = view.findViewById(R.id.navi_bot_cafelate)
        mochaRoute = view.findViewById(R.id.navi_bot_mocha)
        cappRoute = view.findViewById(R.id.navi_bot_cappuccino)

        espressoRoute!!.setOnClickListener {
            val intent = Intent(activity, EspressoActivity::class.java)
            startActivity(intent)

        }

        americanoRoute!!.setOnClickListener {
            val intent = Intent(activity, AmericanoActivity::class.java)
            startActivity(intent)

        }

        irishRoute!!.setOnClickListener {
            val intent = Intent(activity, IrishActivity::class.java)
            startActivity(intent)
        }

        lateRoute!!.setOnClickListener {
            val intent = Intent(activity, LatteActivity::class.java)
            startActivity(intent)
        }

        mochaRoute!!.setOnClickListener {
            val intent = Intent(activity, MochaActivity::class.java)
            startActivity(intent)
        }

        cappRoute!!.setOnClickListener {
            val intent = Intent(activity, CappuccinoActivity::class.java)
            startActivity(intent)
        }


//        val popularRoute = findViewById<View>(R.id.activity_cappuccino_popular_variants_btn)
//        val nutriRoute = findViewById<View>(R.id.activity_cappuccino_nutrition_btn)
//
//        popularRoute.setOnClickListener { toPopular() }
//        nutriRoute.setOnClickListener { toNutri() }


        return view
    }

}