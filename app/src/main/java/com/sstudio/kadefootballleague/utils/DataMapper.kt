package com.sstudio.kadefootballleague.utils

import com.sstudio.kadefootballleague.data.local.entity.FavoriteMatchEntity
import com.sstudio.kadefootballleague.model.Match

object DataMapper {
    fun mapMatchEntitiesToDomainList(input: List<FavoriteMatchEntity>): List<Match> =
        input.map {
            Match(
                idEvent = it.idEvent,
                strEvent = it.strEvent,
                strEventAlternate = null,
                strSport = null,
                idLeague = null,
                strLeague = it.strLeague,
                strSeason = it.strSeason,
                strHomeTeam = it.strHomeTeam,
                strAwayTeam = it.strAwayTeam,
                intHomeScore = it.intHomeScore,
                intRound = it.intRound,
                intAwayScore = it.intAwayScore,
                strHomeRedCards = null,
                strHomeYellowCards = null,
                strHomeFormation = null,
                strAwayRedCards = null,
                strAwayYellowCards = null,
                strAwayFormation = null,
                intHomeShots = null,
                intAwayShots = null,
                strTimestamp = null,
                dateEvent = it.dateEvent,
                strTime = it.strTime,
                idHomeTeam = it.idHomeTeam,
                idAwayTeam = it.idAwayTeam,
                strVenue = it.strVenue,
                strCountry = it.strCountry,
                strThumb = it.strThumb,
                strVideo = it.strVideo,
                strStatus = it.strStatus,
            )
        }

    fun mapDomainToMatchEntities(input: Match): FavoriteMatchEntity =
            FavoriteMatchEntity(
                idEvent = input.idEvent,
                strEvent = input.strEvent,
                strLeague = input.strLeague,
                strSeason = input.strSeason,
                strHomeTeam = input.strHomeTeam,
                strAwayTeam = input.strAwayTeam,
                intHomeScore = input.intHomeScore,
                intRound = input.intRound,
                intAwayScore = input.intAwayScore,
                dateEvent = input.dateEvent,
                strTime = input.strTime,
                idHomeTeam = input.idHomeTeam,
                idAwayTeam = input.idAwayTeam,
                strVenue = input.strVenue,
                strCountry = input.strCountry,
                strThumb = input.strThumb,
                strVideo = input.strVideo,
                strStatus = input.strStatus,
            )
}