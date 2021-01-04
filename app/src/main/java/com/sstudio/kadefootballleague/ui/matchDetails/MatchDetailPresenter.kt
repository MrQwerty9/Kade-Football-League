package com.sstudio.kadefootballleague.ui.matchDetails

import com.sstudio.kadefootballleague.BuildConfig
import com.sstudio.kadefootballleague.data.remote.api.ApiService
import com.sstudio.kadefootballleague.data.remote.response.TeamResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchDetailPresenter(private val view: MatchDetailView, private val apiService: ApiService) {

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
}