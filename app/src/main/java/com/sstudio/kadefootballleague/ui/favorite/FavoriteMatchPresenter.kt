package com.sstudio.kadefootballleague.ui.favorite

import com.sstudio.kadefootballleague.data.local.room.YuLeagueDao
import com.sstudio.kadefootballleague.utils.DataMapper
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteMatchPresenter(private val view: FavoriteMatchView, private val yuLeagueDao: YuLeagueDao) {

    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    fun getAllMatchFavorite(){
        executorService.execute {
            view.showAllMatchFavorite(DataMapper.mapMatchEntitiesToDomainList(yuLeagueDao.getAllMatchFavorite()))
        }
    }
}