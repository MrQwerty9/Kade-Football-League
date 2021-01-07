package com.sstudio.kadefootballleague.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sstudio.kadefootballleague.data.local.entity.FavoriteMatchEntity

@Dao
interface YuLeagueDao {

    @Query("SELECT * FROM FavoriteMatchEntity")
    fun getAllMatchFavorite(): List<FavoriteMatchEntity>

    @Query("SELECT * FROM FavoriteMatchEntity where idEvent = :mIdEvent")
    fun getMatchFavoriteById(mIdEvent: String): List<FavoriteMatchEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMatchFavorite(data: FavoriteMatchEntity)

    @Query("DELETE FROM FavoriteMatchEntity where idEvent = :mIdEvent")
    fun deleteMatchFavorite(mIdEvent: String)
}