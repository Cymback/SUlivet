package com.example.sulivet.sulivet

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class MyProfileFragment : Fragment() {

    companion object {
        const val TAG = "MYPROFILEFRAGMENT"
        fun newInstance() = MyProfileFragment() // singleton
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_myprofile, container, false)
    }
}
