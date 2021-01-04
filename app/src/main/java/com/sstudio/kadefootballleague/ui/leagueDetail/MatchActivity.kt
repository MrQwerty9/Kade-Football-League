package com.sstudio.kadefootballleague.ui.leagueDetail

import android.os.Bundle
import android.view.View
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
import org.jetbrains.anko.toast

class MatchActivity : AppCompatActivity(), LeagueDetailView {
    companion object {
        const val EXTRA_LEAGUE = "position_extra"
        var idLeague: String? = null
    }

    private lateinit var leagueDetailPresenter: LeagueDetailPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)

        setSupportActionBar(appbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0f

        val intent = intent.getParcelableExtra<League>(EXTRA_LEAGUE)
        idLeague = intent?.idLeague
        leagueDetailPresenter = LeagueDetailPresenter(this, ApiConfig.getApiService())
        idLeague?.let { leagueDetailPresenter.getLeagueDetail(it) }
        intent?.strLeague?.let { setToolbarTitle(it) }

        val pagerAdapter =  SectionsPagerAdapter(this, supportFragmentManager)
        with(pager_container_match){
            addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout_match))
            adapter = pagerAdapter
        }
        tab_layout_match.setupWithViewPager(pager_container_match)
        swipe_layout.setOnRefreshListener {
//            recreate()
            finish()
            startActivity(getIntent())
            overridePendingTransition(0, 0)
        }

    }

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
        toast("Error $message")
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
