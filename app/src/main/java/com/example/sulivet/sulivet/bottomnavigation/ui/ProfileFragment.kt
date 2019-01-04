package com.example.sulivet.sulivet.bottomnavigation.ui

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v4.app.Fragment
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat
import android.transition.TransitionManager
import android.view.*
import android.widget.ImageView
import android.widget.TextView

import com.example.sulivet.sulivet.R
import com.google.firebase.auth.FirebaseAuth


class ProfileFragment : Fragment() {

    private var isOpen = false
    private var layout1: ConstraintSet? = null
    private var layout2: ConstraintSet? = null
    private var constraintLayout: ConstraintLayout? = null
    private var imageViewPhoto: ImageView? = null


    private var emailtxt: TextView? = null


    companion object {
        val TAG: String = ProfileFragment::class.java.simpleName
        fun newInstance() = ProfileFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.title = getString(R.string.title_profile)


        hideStatusBar() // called before view is instantiated


        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        emailtxt = view.findViewById(R.id.fragment_profile_email_txt)
        setDataToView()


        layout1 = ConstraintSet()
        layout2 = ConstraintSet()
        imageViewPhoto = view.findViewById(R.id.photo)
        constraintLayout = view.findViewById(R.id.constraint_layout)
        layout2!!.clone(activity, R.layout.fragment_profile_expanded)
        layout1!!.clone(constraintLayout)


        //Handle onclick whats going to happen to photo
        imageViewPhoto!!.setOnClickListener {
            isOpen = if (!isOpen) {
                TransitionManager.beginDelayedTransition(constraintLayout)
                layout2!!.applyTo(constraintLayout)
                !isOpen
            } else {

                TransitionManager.beginDelayedTransition(constraintLayout)
                layout1!!.applyTo(constraintLayout)
                !isOpen

            }


        }
        return view
    }

    private fun setDataToView() {
        val user = FirebaseAuth.getInstance().currentUser

        emailtxt!!.text = "User Email" + user!!.email
    }


    // Hide status bar on android phones.
    @SuppressLint("ObsoleteSdkInt")
    private fun hideStatusBar() {

        if (Build.VERSION.SDK_INT >= 16) {
            activity!!.window.setFlags(AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT, AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT)
            activity!!.window.decorView.systemUiVisibility = 3328
        } else {
            (Window.FEATURE_NO_TITLE)
            activity!!.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
    }
}








