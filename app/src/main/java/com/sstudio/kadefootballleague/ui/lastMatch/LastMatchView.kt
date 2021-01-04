package com.sstudio.kadefootballleague.ui.lastMatch

import com.sstudio.kadefootballleague.model.Match

interface LastMatchView {
    fun showLastMatch(data: List<Match>)
    fun failureLastMatch(message: String)
}