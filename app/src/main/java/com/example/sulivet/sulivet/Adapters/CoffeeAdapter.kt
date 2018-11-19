package com.example.sulivet.sulivet.Adapters

import android.app.Activity
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.sulivet.sulivet.Activities.CustomizeDrinksActivity
import com.example.sulivet.sulivet.Model.CoffeeNames

import com.example.sulivet.sulivet.R
import java.io.PipedOutputStream

class CoffeeAdapter// data is passed into the constructor
(activity: Activity, private val mData: List<CoffeeNames>) : RecyclerView.Adapter<CoffeeAdapter.CoffeeViewHolder>() {

    val a = activity

    // inflates the row layout from xml when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_coffee, parent, false)
        return CoffeeViewHolder(view)
    }

    // binds the data to the TextView in each row
    override fun onBindViewHolder(holder: CoffeeViewHolder, position: Int) {
        holder.myTextView.text = mData[position].name

        Glide.with(holder.myImageView.context)
                .load(mData[position].image)
                .into(holder.myImageView)

        class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
            init {
                view.setOnClickListener {
                    val intent = Intent(view.context, CustomizeDrinksActivity::class.java)
                    view.context.startActivity(intent)
                }
            }
        }
    }

    // total number of rows
    override fun getItemCount(): Int = mData.size

    // stores and recycles views as they are scrolled off screen
    inner class CoffeeViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        internal var myTextView: TextView = itemView.findViewById(R.id.item_coffee_name)
        internal var myImageView: ImageView = itemView.findViewById(R.id.item_coffee_image)

        init {
            //itemView.setOnClickListener(this)
            itemView.setOnClickListener {
                //Lav extra
                CustomizeDrinksActivity.startAsdkjfsa(a, mData[adapterPosition])
            }
        }

        override fun onClick(view: View) {
            //if (mClickListener != null) mClickListener!!.onItemClick(view, adapterPosition)
        }
    }

    // convenience method for getting data at click position
    fun getItem(id: Int): Int {
        return mData.size
    }

    // allows clicks events to be caught
    fun setClickListener(itemClickListener: ItemClickListener) {
        // this.mClickListener = itemClickListener
    }

    // parent activity will implement this method to respond to click events
    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }
}