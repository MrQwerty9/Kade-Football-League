package com.sstudio.kadefootballleague.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team(
    val idTeam: String?,
    val strTeam: String?,
    val intFormedYear: String?,
    val strLeague: String?,
    val idLeague: String?,
    val strLeague2: String?,
    val idLeague2: String?,
    val strLeague3: String?,
    val idLeague3: String?,
    val strLeague4: String?,
    val idLeague4: String?,
    val strLeague5: String?,
    val idLeague5: String?,
    val strLeague6: String?,
    val idLeague6: String?,
    val strLeague7: String?,
    val idLeague7: String?,
    val strDivision: String?,
    val strManager: String?,
    val strStadium: String?,
    val strStadiumThumb: String?,
    val strStadiumDescription: String?,
    val strStadiumLocation: String?,
    val intStadiumCapacity: String?,
    val strWebsite: String?,
    val strFacebook: String?,
    val strTwitter: String?,
    val strInstagram: String?,
    val strDescriptionEN: String?,
    val strCountry: String?,
    val strTeamBadge: String?,
    val strTeamJersey: String?,
    val strTeamLogo: String?,
    val strTeamFanart1: String?,
    val strTeamFanart2: String?,
    val strTeamFanart3: String?,
    val strTeamFanart4: String?,
    val strTeamBanner: String?,
    val strYoutube: String?
): Parcelable