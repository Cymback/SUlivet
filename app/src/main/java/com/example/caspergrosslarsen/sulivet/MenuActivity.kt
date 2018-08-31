package com.example.caspergrosslarsen.sulivet

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.item_menu.view.*

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)


        // TODO:: SET ALL TILES SO THEY LOOK BEAUTIFUL
        header1.headerOne.text = "Desperate tider kræver ultra-billige opskrifter"
        header1.descriptionOne.text = "Dem finder du lige her!"

        // END TILE 1

        header2.headerOne.text = "Skal der kæles for detaljen?"
        header2.descriptionOne.text = "   Så' der dugfriske og en smule peberede opskrifter her"

        // END TILE 2

        header3.headerOne.text = "Savner du inspiration?"
        header3.descriptionOne.text = "Klik på mig og få uanede mængder af inspiration"

        // END TILE 3


        header4.headerOne.text = "Savner du en madplans-generator på et ugentligt plan?"
        header4.descriptionOne.text = "Prøv den her!"

        // END TILE 4

        header5.headerOne.text = "Essentielle køkken-Acessories"
        header5.descriptionOne.text = "Lad os lige skabe en liste af ting, DU SKAL HA'"


    }
}
