package com.sstudio.kadefootballleague.ui.league

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sstudio.kadefootballleague.BuildConfig
import com.sstudio.kadefootballleague.R
import com.sstudio.kadefootballleague.model.League
import kotlinx.android.synthetic.main.item_league.view.*

class LeagueAdapter : RecyclerView.Adapter<LeagueAdapter.LeagueViewHolder>() {

    private var leagues : ArrayList<League> = ArrayList()
    var onItemClick: ((League) -> Unit)? = null

    fun setLeagues(league: List<League>){
        this.leagues.clear()
        this.leagues.addAll(league)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_league, parent, false)
        return LeagueViewHolder(view)
    }

    override fun getItemCount(): Int = leagues.size

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.bindItem(leagues[position])
    }

    inner class LeagueViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        fun bindItem (items : League){
            with(itemView) {
                tv_league_name.text = items.strLeague

                Glide.with(itemView.context)
                    .load(BuildConfig.IMAGE_URL + "league/badge/small/" + items.badgePath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(iv_league_list)
                setOnClickListener {
                    onItemClick?.invoke(items)
                }
            }
        }
    }

}