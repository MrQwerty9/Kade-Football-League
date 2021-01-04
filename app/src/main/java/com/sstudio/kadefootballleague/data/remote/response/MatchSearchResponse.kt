package com.sstudio.kadefootballleague.data.remote.response

import com.sstudio.kadefootballleague.model.Match

data class MatchSearchResponse(
    val event: List<Match>
)