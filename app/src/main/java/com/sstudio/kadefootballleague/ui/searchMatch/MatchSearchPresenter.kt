package com.sstudio.kadefootballleague.ui.searchMatch

import com.sstudio.kadefootballleague.BuildConfig
import com.sstudio.kadefootballleague.data.remote.api.ApiService
import com.sstudio.kadefootballleague.data.remote.response.MatchSearchResponse
import com.sstudio.kadefootballleague.model.Match
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchSearchPresenter(private val view: MatchSearchView, private val apiService: ApiService) {

    fun getMatchSearch(query: String){
        apiService.findMatch(BuildConfig.TSDB_API_KEY, query)
            .enqueue(object : Callback<MatchSearchResponse>{
                override fun onResponse(
                    call: Call<MatchSearchResponse>,
                    response: Response<MatchSearchResponse>
                ) {
                    response.body()?.event?.let {
                        view.showMatchFound(filterSoccer(it))
                    }
                }

                override fun onFailure(call: Call<MatchSearchResponse>, t: Throwable) {
                    t.message?.let { view.failureResponse(it) }
                }

            })
    }

    private fun filterSoccer(match: List<Match>): List<Match>{
        val soccerMatch = ArrayList<Match>()
        for (data in match){
            if (data.strSport == "Soccer"){
                soccerMatch.add(data)
            }
        }
        return soccerMatch
    }
}