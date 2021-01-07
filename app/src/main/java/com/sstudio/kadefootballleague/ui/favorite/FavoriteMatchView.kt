package com.sstudio.kadefootballleague.ui.favorite

import com.sstudio.kadefootballleague.model.Match

interface FavoriteMatchView {
    fun showAllMatchFavorite(data: List<Match>)
    fun failure(message: String)
}