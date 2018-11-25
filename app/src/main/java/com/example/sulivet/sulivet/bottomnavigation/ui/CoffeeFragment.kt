package com.example.sulivet.sulivet.bottomnavigation.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.sulivet.sulivet.R


class CoffeeFragment : Fragment() {

    companion object {
        val TAG: String = CoffeeFragment::class.java.simpleName
        fun newInstance() = CoffeeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.title = getString(R.string.title_coffee)
        val view = inflater.inflate(R.layout.fragment_coffee, container, false)
        return view
    }

}