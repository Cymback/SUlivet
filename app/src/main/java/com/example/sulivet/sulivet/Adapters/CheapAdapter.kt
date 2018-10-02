package com.example.sulivet.sulivet.Adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.sulivet.sulivet.Activities.RecipeDetailActivity
import com.example.sulivet.sulivet.Model.Recipe
import com.example.sulivet.sulivet.R

class CheapAdapter(private val mContext: Context, private val mData: List<Recipe>) : RecyclerView.Adapter<CheapAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view: View
        val mInflater = LayoutInflater.from(mContext)
        view = mInflater.inflate(R.layout.item_recipe, parent, false)


        return MyViewHolder(view)


    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        holder.recipetitle.text = mData[position].title

        Glide.with(holder.itemView.context)
                .load(mData[position].picture)
                .into(holder.recipeimg)


        val animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.fade_in)
        holder.itemView.startAnimation((animation))




        // Set click listener
        holder.cardView.setOnClickListener {

            val intent = Intent(mContext, RecipeDetailActivity::class.java)
            intent.apply {

                // Transfering data to detailactivity
                putExtra(RecipeDetailActivity.EXTRA_TITLE, mData[position].title)
                putExtra(RecipeDetailActivity.EXTRA_LIST_INGREDIENTS, mData[position].ingredients)
                putExtra(RecipeDetailActivity.EXTRA_CONTENT, mData[position].content)
                putExtra(RecipeDetailActivity.EXTRA_PICTURE, mData[position].picture)


            }
            mContext.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
        return mData.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var recipetitle: TextView = itemView.findViewById<View>(R.id.recipe_title_id) as TextView
        internal var recipeimg: ImageView = itemView.findViewById<View>(R.id.recipe_img_id) as ImageView
        var cardView: CardView = itemView.findViewById<View>(R.id.item_recipe_id) as CardView


    }


}

