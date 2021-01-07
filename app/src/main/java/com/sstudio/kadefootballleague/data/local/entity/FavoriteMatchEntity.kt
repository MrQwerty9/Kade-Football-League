package com.sstudio.kadefootballleague.data.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteMatchEntity(
    @PrimaryKey
    @NonNull
    val idEvent: String,
    val strEvent: String?,
    val strLeague: String?,
    val strSeason: String?,
    val strHomeTeam: String?,
    val strAwayTeam: String?,
    val intHomeScore: String?,
    val intRound: String?,
    val intAwayScore: String?,
    val dateEvent: String?,
    val strTime: String?,
    val idHomeTeam: String?,
    val idAwayTeam: String?,
    val strVenue: String?,
    val strCountry: String?,
    val strThumb: String?,
    val strVideo: String?,
    val strStatus: String?
)