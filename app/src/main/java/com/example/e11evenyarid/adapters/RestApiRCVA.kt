package com.example.e11evenyarid.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.e11evenyarid.R
import com.example.e11evenyarid.models.ForRest
import java.util.*

class RestApiRCVA(var context: Context,
                  var forRests: ArrayList<ForRest>
) :
    RecyclerView.Adapter<RestApiRCVA.ViewHolder>() {


    lateinit var listener:  OnItemClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.forestapi, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val forRest = forRests[position]
        holder.post.text = forRest.post
        holder.title.text = forRest.title
        holder.author.text = forRest.author
        holder.created.text = forRest.creationdate
    }

    override fun getItemCount(): Int {
        return forRests.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener{
        var author: TextView = itemView.findViewById(R.id.author)
        var title: TextView = itemView.findViewById(R.id.title)
        var post: TextView = itemView.findViewById(R.id.post)
        var created: TextView = itemView.findViewById(R.id.cdate)

        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            var position:Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }
        interface OnItemClickListener {
            fun onItemClick(position: Int)
        }

}

