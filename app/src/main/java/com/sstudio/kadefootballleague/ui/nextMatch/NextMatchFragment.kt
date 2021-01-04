package com.sstudio.kadefootballleague.ui.nextMatch

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sstudio.kadefootballleague.R
import com.sstudio.kadefootballleague.data.remote.api.ApiConfig
import com.sstudio.kadefootballleague.model.Match
import com.sstudio.kadefootballleague.ui.leagueDetail.MatchActivity
import com.sstudio.kadefootballleague.ui.leagueDetail.MatchAdapter
import com.sstudio.kadefootballleague.ui.matchDetails.MatchDetailsActivity
import kotlinx.android.synthetic.main.fragment_next_match.*
import org.jetbrains.anko.support.v4.toast

class NextMatchFragment : Fragment(), NextMatchView {

    private lateinit var nextMatchPresenter: NextMatchPresenter
    private lateinit var matchAdapter: MatchAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_next_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nextMatchPresenter = NextMatchPresenter(this, ApiConfig.getApiService())
        matchAdapter = MatchAdapter()
        MatchActivity.idLeague?.let { nextMatchPresenter.getNextMatch(it) }

        with(rv_next_match){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = matchAdapter
        }

        matchAdapter.onItemClick = {
            val intent = Intent(context, MatchDetailsActivity::class.java)
            intent.putExtra(MatchDetailsActivity.EXTRA_MATCH, it)
            startActivity(intent)
        }
    }

    override fun nextLastMatch(data: List<Match>) {
        matchAdapter.setMatch(data)
    }

    override fun failureLastMatch(message: String) {
        toast("Error $message")
    }
}