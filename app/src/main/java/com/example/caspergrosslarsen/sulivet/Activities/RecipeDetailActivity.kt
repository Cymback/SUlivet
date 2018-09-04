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
        val EXTRA_LIST_INGREDIENTS = "LIST_INGREDIENTS"
        val EXTRA_CONTENT = "CONTENT"
        val EXTRA_PICTURE = "PICTURE"
    }

    private var tvtitle: TextView? = null
    private var listingredients: TextView? = null
    private var content: TextView? = null
    private var img: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)

        tvtitle = findViewById<View>(R.id.title_id) as TextView?
        listingredients = findViewById<View>(R.id.list_ingredients) as TextView?
        content = findViewById<View>(R.id.content) as TextView?
        img = findViewById<View>(R.id.picture) as ImageView


        // Recieve data
        val intent = intent
        val title = intent.extras!!.getString(EXTRA_TITLE)
        val ingredients = intent.extras!!.getString(EXTRA_LIST_INGREDIENTS)
        val content = intent.extras!!.getString(EXTRA_CONTENT)
        val picture = intent.extras!!.getString(EXTRA_PICTURE)


        // Setting values

        tvtitle!!.text = title
        this.listingredients!!.text = ingredients
        this.content!!.text = content

        Glide.with(this)
                .load(picture)
                .into(img!!)

    }
}