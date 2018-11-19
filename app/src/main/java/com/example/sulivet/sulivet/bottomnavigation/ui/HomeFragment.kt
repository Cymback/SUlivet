package com.example.sulivet.sulivet.bottomnavigation.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.sulivet.sulivet.R


class HomeFragment : Fragment() {

    companion object {
        val TAG: String = HomeFragment::class.java.simpleName
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.title = getString(R.string.title_home)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

}