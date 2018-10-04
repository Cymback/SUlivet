package com.example.sulivet.sulivet.Adapters

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.sulivet.sulivet.Model.Essential

import com.example.sulivet.sulivet.R

class EssentialAdapter(private val mContext: Context, private val mData: List<Essential>) : RecyclerView.Adapter<EssentialAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view: View
        val mInflater = LayoutInflater.from(mContext)
        view = mInflater.inflate(R.layout.item_essential, parent, false)


        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        Glide.with(holder.imageview.context)
                .load(mData[position].image)
                .into(holder.imageview)
    }


    override fun getItemCount(): Int {
        return mData.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var header1essential: TextView = itemView.findViewById(R.id.item_essential_header1)
        internal var description: TextView = itemView.findViewById(R.id.item_essential_description)
        internal var imageview: ImageView = itemView.findViewById(R.id.item_essential_imageview)
        var cardView: CardView = itemView.findViewById(R.id.item_essential_id)

    }
}
