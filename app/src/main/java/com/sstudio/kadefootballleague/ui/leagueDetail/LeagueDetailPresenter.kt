package com.sstudio.kadefootballleague.ui.leagueDetail

import com.sstudio.kadefootballleague.BuildConfig
import com.sstudio.kadefootballleague.data.remote.api.ApiService
import com.sstudio.kadefootballleague.data.remote.response.LeagueDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeagueDetailPresenter(private val view: LeagueDetailView, private val apiService: ApiService) {

    fun getLeagueDetail(idLeague: String){
        apiService.getLeagueDetail(BuildConfig.TSDB_API_KEY, idLeague)
            .enqueue(object : Callback<LeagueDetailResponse>{
                override fun onResponse(
                    call: Call<LeagueDetailResponse>,
                    response: Response<LeagueDetailResponse>
                ) {
                    response.body()?.leagues?.let { view.showLeagueDetail(it.first()) }
                }

                override fun onFailure(call: Call<LeagueDetailResponse>, t: Throwable) {
                    t.message?.let { view.failureLeagueDetail(it) }
                }

            })
    }
}