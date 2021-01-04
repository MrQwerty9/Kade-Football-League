package com.sstudio.kadefootballleague.ui.nextMatch

import com.sstudio.kadefootballleague.model.Match

interface NextMatchView {
    fun nextLastMatch(data: List<Match>)
    fun failureLastMatch(message: String)
}