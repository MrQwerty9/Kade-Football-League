package com.sstudio.kadefootballleague.ui.league

import com.sstudio.kadefootballleague.BuildConfig
import com.sstudio.kadefootballleague.data.remote.api.ApiService
import com.sstudio.kadefootballleague.data.remote.response.LeagueResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeaguePresenter(private val view: LeagueView, private val apiService: ApiService) {

    fun getLeague(){
        apiService.getLeague(BuildConfig.TSDB_API_KEY)
            .enqueue(object : Callback<LeagueResponse>{
                override fun onResponse(
                    call: Call<LeagueResponse>,
                    response: Response<LeagueResponse>
                ) {
                    response.body()?.leagues?.let { view.showLeague(it) }
                }

                override fun onFailure(call: Call<LeagueResponse>, t: Throwable) {
                    t.message?.let { view.failureResponse(it) }
                }

            })
    }
}