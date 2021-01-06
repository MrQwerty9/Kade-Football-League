package com.sstudio.kadefootballleague.ui.leagueDetail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import com.sstudio.kadefootballleague.R
import com.sstudio.kadefootballleague.data.remote.api.ApiConfig
import com.sstudio.kadefootballleague.model.League
import com.sstudio.kadefootballleague.model.LeagueDetail
import kotlinx.android.synthetic.main.activity_match.*
import kotlinx.android.synthetic.main.content_detail_match.*


class MatchActivity : AppCompatActivity(), LeagueDetailView {
    companion object {
        const val EXTRA_LEAGUE = "position_extra"
        var idLeague: String? = null
    }

    private lateinit var leagueDetailPresenter: LeagueDetailPresenter
    private var mIntent: League? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)

        setSupportActionBar(appbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0f

        mIntent = intent.getParcelableExtra(EXTRA_LEAGUE)
        if (mIntent == null){
            mIntent = SavedLeagueDetail.mIntent
        }
        SavedLeagueDetail.mIntent = mIntent

        idLeague = mIntent?.idLeague
        leagueDetailPresenter = LeagueDetailPresenter(this, ApiConfig.getApiService())
        idLeague?.let { leagueDetailPresenter.getLeagueDetail(it) }
        mIntent?.strLeague?.let { setToolbarTitle(it) }

        val pagerAdapter =  SectionsPagerAdapter(this, supportFragmentManager)
        with(pager_container_match){
            addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout_match))
            adapter = pagerAdapter
        }
        tab_layout_match.setupWithViewPager(pager_container_match)
        swipe_layout.setOnRefreshListener {
//            recreate()
            finish()
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putParcelable(EXTRA_LEAGUE, mIntent)
//    }
//
//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        super.onRestoreInstanceState(savedInstanceState)
//        mIntent = savedInstanceState.getParcelable(EXTRA_LEAGUE)
//    }


    override fun showLeagueDetail(data: LeagueDetail) {
        progress_bar.visibility = View.GONE
        Glide.with(this)
            .load(data.strFanart1)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(iv_league_detail)
    }

    override fun failureLeagueDetail(message: String) {
        Toast.makeText(this, "Error $message", Toast.LENGTH_SHORT).show()
    }

    private fun setToolbarTitle(title: String) {
        appbarLayout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            var isShow = true
            var scrollRange = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.title = title
                    isShow = true
                } else if (isShow) {
                    collapsingToolbar.title = " "
                    isShow = false
                }
            }
        })
    }
}
