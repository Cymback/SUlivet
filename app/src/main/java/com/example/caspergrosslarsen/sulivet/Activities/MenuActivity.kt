package com.example.caspergrosslarsen.sulivet.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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
        header1.headerOne.text = "Ultra-billige opskrifter"
        header1.descriptionOne.text = "Dem finder du lige her!"

        // Navigation

        header1.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@MenuActivity, CheapRecipesActivity::class.java)
                intent.apply {
                    this.putExtra(CHEAPRECIPES, "cheap-recipes")
                }
                startActivity(intent)

            }
        })

        header2.headerOne.text = "Skal der kæles for detaljen?"
        header2.descriptionOne.text = "Så er der dugfriske og en smule peberede opskrifter her!"

        header2.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@MenuActivity, ExpensiveRecipesActivity::class.java)
                intent.apply {
                    this.putExtra(EXPENSIVERECIPES, "expensive-recipes")
                }
                startActivity(intent)
            }

        })

        header3.headerOne.text = "Savner du inspiration?"
        header3.descriptionOne.text = "Klik på mig og få uanede mængder af inspiration"

        header3.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@MenuActivity, InspirationActivity::class.java)
                intent.apply {
                    this.putExtra(INSPIRATIONRECIPES, "inspiration-recipes")
                }
                startActivity(intent)
            }
        })



        header4.headerOne.text = "Savner du en madplans-generator på et ugentligt plan?"
        header4.descriptionOne.text = "Prøv den her!"


        header5.headerOne.text = "Essentielle køkken-Acessories"
        header5.descriptionOne.text = "Lad os lige skabe en liste af ting, DU SKAL HA'"


    }
}

