package com.example.sulivet.sulivet.Wizard

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sulivet.sulivet.R

class MyFoodFragment: Fragment() {

    companion object {
        const val TAG = "FOODFRAGMENT"
        fun newInstance() = MyFoodFragment() // singleton
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_nav_myfood, container, false)
    }
}