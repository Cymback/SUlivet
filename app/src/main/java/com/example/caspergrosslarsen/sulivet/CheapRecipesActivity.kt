package com.example.caspergrosslarsen.sulivet

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cheap_recipes.*
import kotlinx.android.synthetic.main.item_recipe.*
import kotlinx.android.synthetic.main.item_recipe.view.*


class CheapRecipesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheap_recipes)


        // imageOne.setImageDrawable()
        food1.textholder.text = "test"

        food2.textholder.text = "test2"

        food3.textholder.text = "test3"

        food4.textholder.text = "test4"


    }
}
