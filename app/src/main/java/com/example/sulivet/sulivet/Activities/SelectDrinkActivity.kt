package com.example.sulivet.sulivet.Activities

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import android.view.View

import com.example.sulivet.sulivet.Adapters.CoffeeAdapter
import com.example.sulivet.sulivet.Model.CoffeeNames
import com.example.sulivet.sulivet.R

import java.util.ArrayList


class SelectDrinkActivity : AppCompatActivity(), CoffeeAdapter.ItemClickListener {

    private lateinit var adapter: CoffeeAdapter

    private lateinit var lstCoffeeNames: MutableList<CoffeeNames>


    companion object {
        fun startActivity(activity: Activity?) {

            if (activity == null || activity.isFinishing) return

            val intent = Intent(activity, SelectDrinkActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_drink)


        // data to populate the RecyclerView with

        lstCoffeeNames = ArrayList()

        lstCoffeeNames.add(CoffeeNames(R.mipmap.espressoicon, "Espresso"))
        lstCoffeeNames.add(CoffeeNames(R.mipmap.cappuccinoicon, "Cappuccino"))
        lstCoffeeNames.add(CoffeeNames(R.mipmap.macciatoicon, "Macciato"))
        lstCoffeeNames.add(CoffeeNames(R.mipmap.macciatoicon, "Mochaccino"))
        lstCoffeeNames.add(CoffeeNames(R.mipmap.mochaicon, "Mocha"))
        lstCoffeeNames.add(CoffeeNames(R.mipmap.latteicon, "Latte"))
        lstCoffeeNames.add(CoffeeNames(R.mipmap.espressoicon, "Irish Coffee"))


        // set up the RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_coffee_names_select_drink)


        val decoration = DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)

        recyclerView.addItemDecoration(decoration)
        decoration.setDrawable(getDrawable(R.drawable.seperator))


        adapter = CoffeeAdapter(this, lstCoffeeNames)
        adapter.setClickListener(this)
        recyclerView.adapter = adapter


    }

    override fun onItemClick(view: View, position: Int) {
        val intent = Intent(this, CustomizeDrinksActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.fadeout)


        //Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show()

    }
}