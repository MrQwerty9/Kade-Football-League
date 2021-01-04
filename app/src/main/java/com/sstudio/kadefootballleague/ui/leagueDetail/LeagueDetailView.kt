package com.sstudio.kadefootballleague.ui.leagueDetail

import com.sstudio.kadefootballleague.model.LeagueDetail

interface LeagueDetailView {
    fun showLeagueDetail(data: LeagueDetail)
    fun failureLeagueDetail(message: String)
}