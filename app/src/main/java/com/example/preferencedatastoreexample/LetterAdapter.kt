package com.example.preferencedatastoreexample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LetterAdapter(var context: Context, private val alphabetList: MutableList<Char>) : RecyclerView.Adapter<LetterAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var itemView=LayoutInflater.from(parent.context).inflate(R.layout.alphabet_item_layout, parent, false)
        return  MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.alphabetTextView.text=alphabetList.get(position).toString()
    }

    override fun getItemCount(): Int {
        return  alphabetList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val alphabetTextView: TextView = itemView.findViewById(R.id.alphabet_textview)
    }

}