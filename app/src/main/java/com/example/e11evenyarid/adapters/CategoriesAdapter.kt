package com.example.e11evenyarid.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.e11evenyarid.R

class CategoriesAdapter(
    var context: Context,
    var images: IntArray,
    var itemnames: Array<String>
) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {
    var listener: Listener? = null

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var itemImageView: ImageView = itemView.findViewById(R.id.itemImage)
        var itemNameView: TextView = itemView.findViewById(R.id.itemName)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.itemdisplay, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.itemImageView.setImageResource(images[position])
        holder.itemNameView.text = itemnames[position]
        holder.itemView.setOnClickListener {
            if (listener != null) {
                listener!!.onClick(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }

    interface Listener {
        fun onClick(position: Int)
    }

}