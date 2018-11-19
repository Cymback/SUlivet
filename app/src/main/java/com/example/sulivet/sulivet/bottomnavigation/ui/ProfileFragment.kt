package com.example.sulivet.sulivet.bottomnavigation.ui

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.sulivet.sulivet.R


class ProfileFragment : Fragment() {

    companion object {
        val TAG: String = ProfileFragment::class.java.simpleName
        fun newInstance() = ProfileFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.title = getString(R.string.title_profile)
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        return view
    }

}