package com.sstudio.kadefootballleague

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League(
    var name: String,
    var description: String,
    var photo: Int
): Parcelable