package com.example.sulivet.sulivet.Adapters

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
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

        holder.essential_title.text = mData[position].title

        Glide.with(holder.itemView.context)
                .load(mData[position].image)
                .into(holder.essential_image)



        val animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.fade_in)
        holder.itemView.startAnimation((animation))
    }


    override fun getItemCount(): Int {
        return mData.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var essential_title: TextView = itemView.findViewById(R.id.essential_title_id)
        internal var essential_image: ImageView = itemView.findViewById(R.id.essential_img_id)
        var cardView: CardView = itemView.findViewById(R.id.item_essential_id)

    }
}
