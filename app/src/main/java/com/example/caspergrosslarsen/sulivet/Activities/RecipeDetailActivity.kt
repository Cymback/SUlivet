package com.example.caspergrosslarsen.sulivet.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

import com.example.caspergrosslarsen.sulivet.R

class RecipeDetailActivity : AppCompatActivity() {

    companion object {
        val EXTRA_TITLE = "TITLE"
        val EXTRA_SUBTITLE = "SUBTITLE"
        val EXTRA_CONTENT = "CONTENT"
        val EXTRA_PICTURE = "PICTURE"
    }

    private var tvtitle: TextView? = null
    private var subtitle: TextView? = null
    private var content: TextView? = null
    private var img: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)

        tvtitle = findViewById<View>(R.id.title_id) as TextView?
        subtitle = findViewById<View>(R.id.subtitle) as TextView?
        content = findViewById<View>(R.id.content) as TextView?
        img = findViewById<View>(R.id.picture) as ImageView


        // Recieve data
        val intent = intent
        val title = intent.extras!!.getString(EXTRA_TITLE)
        val subtitle = intent.extras!!.getString(EXTRA_SUBTITLE)
        val content = intent.extras!!.getString(EXTRA_CONTENT)
        val picture = intent.extras!!.getString(EXTRA_PICTURE)


        // Setting values

        tvtitle!!.text = title
        this.subtitle!!.text = subtitle
        this.content!!.text = content

        Glide.with(this)
                .load(picture)
                .into(img!!)

    }
}