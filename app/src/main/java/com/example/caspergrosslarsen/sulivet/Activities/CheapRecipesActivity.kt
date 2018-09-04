package com.example.caspergrosslarsen.sulivet.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.caspergrosslarsen.sulivet.Model.Recipe
import com.example.caspergrosslarsen.sulivet.R
import com.example.caspergrosslarsen.sulivet.Adapters.RecyclerViewAdapter
import java.util.ArrayList


class CheapRecipesActivity : AppCompatActivity() {


    lateinit var lstRecipe: MutableList<Recipe>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheap_recipes)


        lstRecipe = ArrayList<Recipe>()

        lstRecipe.add(Recipe("Fuldkorns pandekager",

                "1 dl grahamsmel\n" +
                        "1 dl mælk\n" +
                        "1 æg\n" +
                        "1 tsk bagepulver",

                "pisk ingredienserne sammen. og steg 2 pandekager i en smule fedtstof.\n" +
                        "\n" +
                        "Pandekagerne er nemme, sunde og smager fantastisk til det meste. Det kan være smøreost og asparges, tomater - laks hvis det skal være fint\n" +
                        "men det kan også være sirup, bananer, sukker.. :) kan anvendes som hovedret eller dessert alt efter fyld.",

                "https://firebasestorage.googleapis.com/v0/b/sulivet-9a546.appspot.com/o/fuldkornpandekage.jpg?alt=media&token=712c3493-a83a-462b-938e-8c92cb950a6e"))

        lstRecipe.add(Recipe("Kylling i Cola",

                "4 dl cola\n" +
                        "4 dl ketchup\n" +
                        "2 spsk paprika\n" +
                        "peber\n" +
                        "900 g kyllingebryster",

                "Bland cola, ketchup og paprika sammen. Smag til med peber.\n" +
                        "\n" +
                        "Kom kyllingen i et ildfast fad og hæld marinaden over.\n" +
                        "\n" +
                        "Bag i ovnen ved 180 grader i ca. 40 minutter.\n" +
                        "\n" + "Servér f.eks. med ris eller blomkålsris og det her lækre og flotte grøntsagsfad med smeltet ost på toppen.",

                "https://firebasestorage.googleapis.com/v0/b/sulivet-9a546.appspot.com/o/kyllingicola.jpeg?alt=media&token=c9a2f4ee-0384-4286-ad64-f2bf96fcaf20"))


        lstRecipe.add(Recipe("Æggekage",

                "15 æg\n" +
                        "1⁄2 øl\n" +
                        "stegt flæsk\n" +
                        "purløg\n" +
                        "tomater\n" +
                        "salt\n" +
                        "peber",

                "Æg og øl piskes sammen og hældes på panden, hvorefter der tilsættes salt og peber. Skal steges ved svag varme. Den er utrolig let og luftig og lækker i smagen. Tilbehøret er masser af stegt flæsk, purløg og tomater.\n" +
                        "Spis evt. groft brød til.\n" +
                        "\n",

                "https://firebasestorage.googleapis.com/v0/b/sulivet-9a546.appspot.com/o/%C3%A6ggekage.jpeg?alt=media&token=6523d4c0-ce38-4188-b596-ffa83dcbce85"))


        // TODO:: Add more recipes

        val myrecView = findViewById<View>(R.id.recyclerview_id) as RecyclerView
        val myAdapter = RecyclerViewAdapter(this, lstRecipe)
        myrecView.layoutManager = GridLayoutManager(this, 3)
        myrecView.adapter = myAdapter
    }
}
