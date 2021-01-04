package com.sstudio.kadefootballleague.ui.league

import com.sstudio.kadefootballleague.model.League

interface LeagueView {
    fun showLeague(data: List<League>)

    fun showLeagueFound(data: List<League>)
    fun failureResponse(message: String)
}