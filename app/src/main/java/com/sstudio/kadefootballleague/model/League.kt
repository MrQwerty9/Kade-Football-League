package com.sstudio.kadefootballleague.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League(
    val idLeague: String,
    val strLeague: String,
    val badgePath: String
): Parcelable