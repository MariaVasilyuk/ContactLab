package com.example.vasilyuk

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class RecyclerAdapter(
    private val list: List<Contact>,
    // передаём коллбек нажатия на кнопку
    private val newAct: (id: Int) -> Unit,
    private val delContact: (id: Int) -> Unit
) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = list[position].firstName

        holder.button1.setOnClickListener {
            newAct(holder.adapterPosition)
        }
        holder.buttonDel.setOnClickListener {
            delContact(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.xxx)
        val buttonDel = itemView.findViewById<Button>(R.id.buttonDel)
        val button1 = itemView.findViewById<Button>(R.id.button1)
    }
}