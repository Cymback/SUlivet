package com.example.sulivet.sulivet.bottomnavigation.ui

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.sulivet.sulivet.Activities.SelectDrinkActivity
import com.example.sulivet.sulivet.CoffeeActivities.CoffeeOverview

import com.example.sulivet.sulivet.R


class HomeFragment : Fragment() {

    // Global Variables
    private var textToCoffeeActivities: TextView? = null
    private var textToOrderCoffee: TextView? = null

    companion object {
        val TAG: String = HomeFragment::class.java.simpleName
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.title = getString(R.string.title_home)

        val myFragmentView = inflater.inflate(R.layout.fragment_home, container, false)

        // Initializing
        textToCoffeeActivities = myFragmentView.findViewById(R.id.home_frag_coffe_txt)
        textToOrderCoffee = myFragmentView.findViewById(R.id.home_frag_order_txt)


        textToCoffeeActivities!!.setOnClickListener { it ->
            val intent = Intent(activity, CoffeeOverview::class.java)
            startActivity(intent)
            activity!!.overridePendingTransition(R.anim.slide_out_right, R.anim.slide_out_right)

            textToOrderCoffee!!.setOnClickListener {
                val intent = Intent(activity, SelectDrinkActivity::class.java)
                startActivity(intent)
                activity!!.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
            }

        }

        return myFragmentView


    }

}