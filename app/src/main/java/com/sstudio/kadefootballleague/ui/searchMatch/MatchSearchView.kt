package com.sstudio.kadefootballleague.ui.searchMatch

import com.sstudio.kadefootballleague.model.Match

interface MatchSearchView {
    fun showMatchFound(data: List<Match>)
    fun failureResponse(message: String)
}