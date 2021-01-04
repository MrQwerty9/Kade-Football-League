package com.sstudio.kadefootballleague.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Match(
    val idEvent: String?,
    val strEvent: String?,
    val strEventAlternate: String?,
    val idLeague: String?,
    val strLeague: String?,
    val strSeason: String?,
    val strHomeTeam: String?,
    val strAwayTeam: String?,
    val intHomeScore: String?,
    val intRound: String?,
    val intAwayScore: String?,
    val strHomeRedCards: String?,
    val strHomeYellowCards: String?,
    val strHomeFormation: String?,
    val strAwayRedCards: String?,
    val strAwayYellowCards: String?,
    val strAwayFormation: String?,
    val intHomeShots: String?,
    val intAwayShots: String?,
    val strTimestamp: String?,
    val dateEvent: String?,
    val strTime: String?,
    val idHomeTeam: String?,
    val idAwayTeam: String?,
    val strVenue: String?,
    val strCountry: String?,
    val strThumb: String?,
    val strVideo: String?,
    val strStatus: String?
): Parcelable