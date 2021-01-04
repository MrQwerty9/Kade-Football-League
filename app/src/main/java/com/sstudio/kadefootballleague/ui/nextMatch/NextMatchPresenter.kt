package com.sstudio.kadefootballleague.ui.nextMatch

import com.sstudio.kadefootballleague.BuildConfig
import com.sstudio.kadefootballleague.data.remote.api.ApiService
import com.sstudio.kadefootballleague.data.remote.response.MatchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NextMatchPresenter(private val view: NextMatchView, private val apiService: ApiService) {

    fun getNextMatch(idLeague: String){
        apiService.getNextMatch(BuildConfig.TSDB_API_KEY, idLeague)
            .enqueue(object : Callback<MatchResponse>{
                override fun onResponse(
                    call: Call<MatchResponse>,
                    response: Response<MatchResponse>
                ) {
                    response.body()?.events?.let { view.nextLastMatch(it) }
                }

                override fun onFailure(call: Call<MatchResponse>, t: Throwable) {
                    t.message?.let { view.failureLastMatch(it) }
                }

            })
    }
}