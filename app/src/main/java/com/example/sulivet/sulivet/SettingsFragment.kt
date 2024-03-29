package com.example.sulivet.sulivet

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class SettingsFragment : Fragment() {

    companion object {
        const val TAG = "SETTINGSFRAGMENT"
        fun newInstance() = SettingsFragment() // singleton
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_nav_settings, container, false)
    }
}