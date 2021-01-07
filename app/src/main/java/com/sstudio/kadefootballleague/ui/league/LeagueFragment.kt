package com.sstudio.kadefootballleague.ui.league

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.sstudio.kadefootballleague.R
import com.sstudio.kadefootballleague.ui.leagueDetail.MatchActivity
import com.sstudio.kadefootballleague.ui.searchMatch.MatchSearchActivity
import com.sstudio.kadefootballleague.utils.LeagueList
import kotlinx.android.synthetic.main.fragment_league.*
import org.jetbrains.anko.support.v4.startActivity

class LeagueFragment : Fragment() {

    private lateinit var leagueAdapter: LeagueAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_league, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        leagueAdapter = LeagueAdapter()
        leagueAdapter.setLeagues(LeagueList.getLeagueList())
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home_activity_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.search -> {
                startActivity<MatchSearchActivity>()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}