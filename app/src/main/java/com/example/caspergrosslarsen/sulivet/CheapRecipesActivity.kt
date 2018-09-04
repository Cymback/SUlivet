package com.example.caspergrosslarsen.sulivet

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.caspergrosslarsen.sulivet.Model.Recipe
import java.util.ArrayList


class CheapRecipesActivity : AppCompatActivity() {


    lateinit var lstRecipe: MutableList<Recipe>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheap_recipes)


        lstRecipe = ArrayList<Recipe>()

        lstRecipe.add(Recipe("Flødekartofler med sovs", "Dejligt og smagfuldt", "Du skal gøre følgende: Kog pasta, skrald kartofler, bla bla.", "https://i.imgur.com/0KVST4x.gif"))
        lstRecipe.add(Recipe("Flødekartofler med sovs", "Dejligt og smagfuldt", "Du skal gøre følgende: Kog pasta, skrald kartofler, bla bla.", "https://i.imgur.com/5Mc6MQo.gif"))
        // lstRecipe.add(Recipe("Flødekartofler med sovs", "Dejligt og smagfuldt", "Du skal gøre følgende: Kog pasta, skrald kartofler, bla bla.", R.drawable.test3))
        lstRecipe.add(Recipe("Flødekartofler med sovs", "Dejligt og smagfuldt", "Du skal gøre følgende: Kog pasta, skrald kartofler, bla bla.", "https://i.imgur.com/P3hkC7Q.gif"))
        lstRecipe.add(Recipe("Flødekartofler med sovs", "Dejligt og smagfuldt", "Du skal gøre følgende: Kog pasta, skrald kartofler, bla bla.", "https://i.imgur.com/0KVST4x.gif"))

        val myrv = findViewById<View>(R.id.recyclerview_id) as RecyclerView
        val myAdapter = RecyclerViewAdapter(this, lstRecipe)
        myrv.layoutManager = GridLayoutManager(this, 3)
        myrv.adapter = myAdapter
    }
}
