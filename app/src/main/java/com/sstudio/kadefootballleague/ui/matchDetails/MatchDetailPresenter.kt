package com.sstudio.kadefootballleague.ui.matchDetails

import com.sstudio.kadefootballleague.BuildConfig
import com.sstudio.kadefootballleague.data.local.room.YuLeagueDao
import com.sstudio.kadefootballleague.data.remote.api.ApiService
import com.sstudio.kadefootballleague.data.remote.response.TeamResponse
import com.sstudio.kadefootballleague.model.Match
import com.sstudio.kadefootballleague.utils.DataMapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MatchDetailPresenter(private val view: MatchDetailView, private val apiService: ApiService, private val yuLeagueDao: YuLeagueDao) {

    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    fun getTeamHome(idTeam: String){
        apiService.getTeam(BuildConfig.TSDB_API_KEY, idTeam)
            .enqueue(object : Callback<TeamResponse>{
                override fun onResponse(
                    call: Call<TeamResponse>,
                    response: Response<TeamResponse>
                ) {
                    response.body()?.teams?.first()?.let { view.showClubHome(it) }
                }

                override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                    t.message?.let { view.failureClubHome(it) }
                }
            })
    }

    fun getTeamAway(idTeam: String){
        apiService.getTeam(BuildConfig.TSDB_API_KEY, idTeam)
            .enqueue(object : Callback<TeamResponse>{
                override fun onResponse(
                    call: Call<TeamResponse>,
                    response: Response<TeamResponse>
                ) {
                    response.body()?.teams?.first()?.let { view.showClubAway(it) }
                }

                override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                    t.message?.let { view.failureClubAway(it) }
                }
            })
    }

    fun changeLocalDate(date: String?, time: String?): String{
        if (date == null || time == null){
            return date ?: ""
        }
        val dfZone = SimpleDateFormat("yyyy-MM-dd HH:mm:ss z")
        val dateFormat = SimpleDateFormat.getDateInstance(0, Locale("id"))
        val dZulu: Date = dfZone.parse("$date $time UTC")

        dfZone.timeZone = TimeZone.getDefault()
        return dateFormat.format(dZulu)
    }

    fun changeLocalTime(date: String?, time: String?): String{
        if (date == null || time == null){
            return time ?: ""
        }
        val dfZone = SimpleDateFormat("yyyy-MM-dd HH:mm:ss z")
        val timeFormat = SimpleDateFormat.getTimeInstance(3, Locale("id"))
        val dZulu: Date = dfZone.parse("$date $time UTC")

        dfZone.timeZone = TimeZone.getDefault()
        return timeFormat.format(dZulu)
    }

    fun getFavoriteStatus(idEvent: String) {
        executorService.execute {
            view.showFavoriteMatch(
                DataMapper.mapMatchEntitiesToDomainList(
                    yuLeagueDao.getMatchFavoriteById(
                        idEvent
                    )
                )
            )
        }
    }

    fun insertFavorite(match: Match){
        executorService.execute {
            yuLeagueDao.insertMatchFavorite(DataMapper.mapDomainToMatchEntities(match))
        }
    }

    fun deleteFavorite(match: Match){
        executorService.execute {
            match.idEvent.let { yuLeagueDao.deleteMatchFavorite(it) }
        }
    }
}