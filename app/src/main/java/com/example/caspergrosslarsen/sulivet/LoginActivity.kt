package com.example.caspergrosslarsen.sulivet

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.caspergrosslarsen.sulivet.Wizard.WizardOne
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {


    lateinit var button: Button

    companion object {
        val GLGTOWIZ = "GLGTOWIZ" // key identifier for toWizardTwo intent

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tmpBtn.setOnClickListener { toWizardOne() }


    }


    private fun toWizardOne() {


        val intent = Intent(this@LoginActivity, WizardOne::class.java)
        startActivity(intent)


    }
}
