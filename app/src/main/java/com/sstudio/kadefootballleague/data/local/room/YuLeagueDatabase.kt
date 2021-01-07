package com.sstudio.kadefootballleague.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sstudio.kadefootballleague.data.local.entity.FavoriteMatchEntity

@Database(entities = [FavoriteMatchEntity::class],
    version = 1,
    exportSchema = false)
abstract class YuLeagueDatabase : RoomDatabase() {
    abstract fun yuLeagueDao(): YuLeagueDao

    companion object{
        @Volatile
        private var INSTANCE: YuLeagueDatabase? = null

        fun getInstance(context: Context): YuLeagueDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(context.applicationContext,
                    YuLeagueDatabase::class.java,
                    "yuLeagueDb.db")
                    .build()
            }
    }


}