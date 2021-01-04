package com.sstudio.kadefootballleague.utils

import com.sstudio.kadefootballleague.model.League
import java.util.*

object LeagueList {
    fun getLeagueList(): List<League> {
        val leagues = ArrayList<League>()

        leagues.add(League("4480", "UEFA Champions League", "dtp1dl1534767233.png"))

        leagues.add(League("4346", "American Major League Soccer", "dqo6r91549878326.png"))

        leagues.add(League("4328", "English Premier League", "i6o0kh1549879062.png"))

        leagues.add(League("4334", "French Ligue 1", "2yo0vn1592927519.png"))

        leagues.add(League("4331", "German Bundesliga", "0j55yv1534764799.png"))

        leagues.add(League("4332", "Italian Serie A", "ocy2fe1566216901.png"))

        leagues.add(League("4335", "Spanish La Liga", "7onmyv1534768460.png"))

        return leagues
    }
}