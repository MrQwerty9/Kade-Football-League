package com.sstudio.kadefootballleague.ui.matchDetails

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sstudio.kadefootballleague.R
import com.sstudio.kadefootballleague.data.remote.api.ApiConfig
import com.sstudio.kadefootballleague.model.Match
import com.sstudio.kadefootballleague.model.Team
import kotlinx.android.synthetic.main.activity_match_details.*
import org.jetbrains.anko.toast

class MatchDetailsActivity : AppCompatActivity(), MatchDetailView {
    companion object{
        val EXTRA_MATCH = "extra_match"
    }

    private lateinit var matchDetailPresenter: MatchDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_details)

        val intent = intent.getParcelableExtra<Match>(EXTRA_MATCH)

        toolbar.title = intent?.strEvent
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0f


        matchDetailPresenter = MatchDetailPresenter(this, ApiConfig.getApiService())
        intent?.idHomeTeam?.let { matchDetailPresenter.getTeamHome(it) }
        intent?.idAwayTeam?.let { matchDetailPresenter.getTeamAway(it) }

        intent?.let { showMatchDetail(it) }
    }

    private fun showMatchDetail(data: Match) {
        Glide.with(this)
            .load(data.strThumb)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(iv_match_detail)
        tv_league_name.text = data.strLeague + ", Ronde " + data.intRound
        tv_venue_name.text = data.strVenue + ", " + data.strCountry
        tv_match_date.text = matchDetailPresenter.changeLocalDate(data.dateEvent, data.strTime)
        tv_match_time.text = matchDetailPresenter.changeLocalTime(data.dateEvent, data.strTime)
        tv_club_home.text = data.strHomeTeam
        tv_club_away.text = data.strAwayTeam
        tv_score_home.text = data.intHomeScore ?: "-"
        tv_score_away.text = data.intAwayScore ?: "-"
        if (data.strStatus == "Match Finished"){
            tv_ft.visibility = View.VISIBLE
        }
    }

    override fun showClubHome(data: Team) {
        Glide.with(this)
            .load(data.strTeamBadge)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(iv_match_home)
    }

    override fun failureClubHome(message: String) {
        toast("Error $message")
    }

    override fun showClubAway(data: Team) {
        Glide.with(this)
            .load(data.strTeamBadge)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(iv_match_away)
    }

    override fun failureClubAway(message: String) {
        toast("Error $message")
    }
}