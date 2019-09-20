package com.sstudio.kadefootballleague

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.jetbrains.anko.AnkoContext

class LeagueAdapter(var list : MutableList<League>, var listener : (League) -> Unit) : RecyclerView.Adapter<LeagueAdapter.LeagueViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        return LeagueViewHolder(LeagueUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.bindItem(list[position], listener)
    }

    inner class LeagueViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        private val logo = itemView.findViewById<ImageView>(LeagueUI.photo)
        private val name = itemView.findViewById<TextView>(LeagueUI.nameLeague)

        fun bindItem (items : League, listener : (League) -> Unit){
            name.text = items.name
            Glide.with(itemView.context).load(items.photo).into(logo)
            itemView.setOnClickListener {
                listener(items)
            }
        }
    }

}