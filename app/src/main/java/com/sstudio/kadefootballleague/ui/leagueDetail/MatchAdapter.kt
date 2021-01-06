package com.sstudio.kadefootballleague.ui.leagueDetail

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sstudio.kadefootballleague.R
import com.sstudio.kadefootballleague.model.Match
import kotlinx.android.synthetic.main.item_match.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class MatchAdapter : RecyclerView.Adapter<MatchAdapter.LeagueViewHolder>() {

    private var match : ArrayList<Match> = ArrayList()
    var onItemClick: ((Match) -> Unit)? = null

    fun setMatch(match: List<Match>){
        this.match.clear()
        this.match.addAll(match)
        Log.d("mytag", "adapter $match")
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false)
        return LeagueViewHolder(view)
    }

    override fun getItemCount(): Int = match.size

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.bindItem(match[position])
    }

    inner class LeagueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bindItem(items: Match){
            with(itemView) {
                tv_league_name.text = items.strLeague
                tv_club_home.text = items.strHomeTeam
                tv_club_away.text = items.strAwayTeam
                tv_score_home.text = items.intHomeScore ?: "-"
                tv_score_away.text = items.intAwayScore ?: "-"
                tv_match_date.text = changeLocalDate(items.dateEvent, items.strTime)
                tv_match_time.text = changeLocalTime(items.dateEvent, items.strTime)
                tv_venue_name.text = items.strVenue
                if (items.strStatus == "Match Finished"){
                    tv_ft.visibility = View.VISIBLE
                }

                itemView.setOnClickListener {
                    onItemClick?.invoke(items)
                }
            }
        }
    }

    private fun changeLocalDate(date: String?, time: String?): String{
        if (date == null || time == null){
            return date ?: ""
        }
        val dfZone = SimpleDateFormat("yyyy-MM-dd HH:mm:ss z")
        val dateFormat = SimpleDateFormat.getDateInstance(0, Locale("id"))
        val dZulu: Date = dfZone.parse("$date $time UTC")

        dfZone.timeZone = TimeZone.getDefault()
        return dateFormat.format(dZulu)
    }

    private fun changeLocalTime(date: String?, time: String?): String{
        if (date == null || time == null){
            return time ?: ""
        }
        val dfZone = SimpleDateFormat("yyyy-MM-dd HH:mm:ss z")
        val timeFormat = SimpleDateFormat.getTimeInstance(3, Locale("id"))
        val dZulu: Date = dfZone.parse("$date $time UTC")

        dfZone.timeZone = TimeZone.getDefault()
        return timeFormat.format(dZulu)
    }

}