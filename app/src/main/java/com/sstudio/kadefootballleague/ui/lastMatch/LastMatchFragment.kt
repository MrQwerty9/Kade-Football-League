package com.sstudio.kadefootballleague.ui.lastMatch

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sstudio.kadefootballleague.R
import com.sstudio.kadefootballleague.data.remote.api.ApiConfig
import com.sstudio.kadefootballleague.model.Match
import com.sstudio.kadefootballleague.ui.leagueDetail.MatchActivity
import com.sstudio.kadefootballleague.ui.leagueDetail.MatchAdapter
import com.sstudio.kadefootballleague.ui.matchDetails.MatchDetailsActivity
import kotlinx.android.synthetic.main.fragment_last_match.*

class LastMatchFragment : Fragment(), LastMatchView {

    private lateinit var lastMatchPresenter: LastMatchPresenter
    private lateinit var matchAdapter: MatchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_last_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lastMatchPresenter = LastMatchPresenter(this, ApiConfig.getApiService())
        matchAdapter = MatchAdapter()
        MatchActivity.idLeague?.let { lastMatchPresenter.getLastMatch(it) }

        with(rv_last_match){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = matchAdapter
        }

        matchAdapter.onItemClick = {
            val intent = Intent(activity, MatchDetailsActivity::class.java)
            intent.putExtra(MatchDetailsActivity.EXTRA_MATCH, it)
            startActivity(intent)
        }
    }

    override fun showLastMatch(data: List<Match>) {
        matchAdapter.setMatch(data)
    }

    override fun failureLastMatch(message: String) {
        Toast.makeText(context, "Error $message", Toast.LENGTH_SHORT).show()
    }
}