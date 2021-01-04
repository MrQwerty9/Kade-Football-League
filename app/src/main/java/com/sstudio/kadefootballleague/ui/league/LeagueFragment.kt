package com.sstudio.kadefootballleague.ui.league

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.sstudio.kadefootballleague.R
import com.sstudio.kadefootballleague.ui.home.LeagueAdapter
import com.sstudio.kadefootballleague.ui.leagueDetail.MatchActivity
import com.sstudio.kadefootballleague.utils.LeagueList
import kotlinx.android.synthetic.main.fragment_league.*

class LeagueFragment : Fragment() {

    private lateinit var leagueAdapter: LeagueAdapter
    private lateinit var leaguePresenter: LeaguePresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_league, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        context.setSupportActionBar(toolbar)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.elevation = 0f

        leagueAdapter = LeagueAdapter()
        leagueAdapter.setLeagues(LeagueList.getLeagueList())
//        leaguePresenter = LeaguePresenter(this, ApiConfig.getApiService())
        with(rv_league){
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = leagueAdapter
        }

        leagueAdapter.onItemClick = { selectedData ->
            val intent = Intent(context, MatchActivity::class.java)
            intent.putExtra(MatchActivity.EXTRA_LEAGUE, selectedData)
            startActivity(intent)
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
//        inflater.inflate(R.menu.toolbar_menu,menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId){
//            R.id.search -> {
//                startActivity<MatchSearchActivity>()
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }

}