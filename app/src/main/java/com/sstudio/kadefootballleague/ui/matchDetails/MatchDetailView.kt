package com.sstudio.kadefootballleague.ui.matchDetails

import com.sstudio.kadefootballleague.model.Team

interface MatchDetailView {
    fun showClubHome(data: Team)
    fun failureClubHome(message: String)
    fun showClubAway(data: Team)
    fun failureClubAway(message: String)
}