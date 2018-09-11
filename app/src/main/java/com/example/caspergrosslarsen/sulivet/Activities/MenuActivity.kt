package com.example.caspergrosslarsen.sulivet.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.view.View
import com.example.caspergrosslarsen.sulivet.R
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.item_menu.view.*

class MenuActivity : AppCompatActivity() {

    companion object {
        const val CHEAPRECIPES = "CHEAPRECIPES" // key identifiers used in intents
        const val EXPENSIVERECIPES = "EXPENSIVERECIPES"
        const val INSPIRATIONRECIPES = "INSPIRATIONRECIPES"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)


        // TODO:: SET ALL TILES SO THEY LOOK BEAUTIFUL
        header1.headerOne.text = getString(R.string.menu_ultra_cheap_recipes)
        header1.descriptionOne.text = getString(R.string.menu_you_will_find_them_here)

        // Navigation

        header1.setOnClickListener {
            CheapRecipesActivity.startActivity(this@MenuActivity)
        }

        header2.headerOne.text = getString(R.string.menu_should_care_for)
        header2.descriptionOne.text = getString(R.string.menu_fresh_recipes)

        header2.setOnClickListener {
            ExpensiveRecipesActivity.startActivity(this@MenuActivity)
        }

        header3.headerOne.text = getString(R.string.menu_do_you_lack_inspiration)
        header3.descriptionOne.text = getString(R.string.menu_klik_me_get_inspiration)

        header3.setOnClickListener {
            InspirationActivity.startActivity(this@MenuActivity)

        }

        header4.headerOne.text = getString(R.string.menu_do_you_miss_food_generator)
        header4.descriptionOne.text = getString(R.string.menu_try_this)

        header4.setOnClickListener {
            FoodGeneratorActivity.startActivity(this@MenuActivity)
        }


        header5.headerOne.text = getString(R.string.menu_essential_kitchen_stuff)
        header5.descriptionOne.text = getString(R.string.menu_list_of_kitchen_things)

        header5.setOnClickListener {
            EssentialActivity.startActivity(this@MenuActivity)

        }
    }
}


