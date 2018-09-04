package com.example.caspergrosslarsen.sulivet.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.caspergrosslarsen.sulivet.Model.Recipe
import com.example.caspergrosslarsen.sulivet.R

class RecyclerViewAdapter(private val mContext: Context, private val mData: List<Recipe>) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view: View
        val mInflater = LayoutInflater.from(mContext)
        view = mInflater.inflate(R.layout.item_recipe, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.recipe_title.text = mData[position].title

        Glide.with(holder.itemView.context)
                .load(mData[position].picture)
                .into(holder.recipe_img)

    }

    override fun getItemCount(): Int {
        return mData.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var recipe_title: TextView
        internal var recipe_img: ImageView

        init {

            recipe_title = itemView.findViewById<View>(R.id.recipe_title_id) as TextView
            recipe_img = itemView.findViewById<View>(R.id.recipe_img_id) as ImageView
        }
    }
}
