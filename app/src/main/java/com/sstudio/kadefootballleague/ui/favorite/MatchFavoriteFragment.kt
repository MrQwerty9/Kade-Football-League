package com.sstudio.kadefootballleague.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sstudio.kadefootballleague.R
import com.sstudio.kadefootballleague.data.local.room.YuLeagueDatabase
import com.sstudio.kadefootballleague.model.Match
import com.sstudio.kadefootballleague.ui.leagueDetail.MatchAdapter
import com.sstudio.kadefootballleague.ui.matchDetails.MatchDetailsActivity
import kotlinx.android.synthetic.main.fragment_match_favorite.*
import org.jetbrains.anko.support.v4.runOnUiThread

class MatchFavoriteFragment : Fragment(), FavoriteMatchView {

    private lateinit var matchFavoriteMatchPresenter: FavoriteMatchPresenter
    private lateinit var matchAdapter: MatchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_match_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let {
            val database = YuLeagueDatabase.getInstance(it)
            matchFavoriteMatchPresenter = FavoriteMatchPresenter(this, database.yuLeagueDao())
        }

        dataAdapterHandler()
    }

    private fun dataAdapterHandler() {
        matchAdapter = MatchAdapter()
        matchFavoriteMatchPresenter.getAllMatchFavorite()

        with(rv_match_favorite){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = matchAdapter
        }
        matchAdapter.notifyDataSetChanged()
        matchAdapter.onItemClick = {
            val intent = Intent(context, MatchDetailsActivity::class.java)
            intent.putExtra(MatchDetailsActivity.EXTRA_MATCH, it)
            startActivity(intent)
        }
    }

    override fun showAllMatchFavorite(data: List<Match>) {
        runOnUiThread {
            progress_bar.visibility = View.GONE
            matchAdapter.setMatch(data)
        }
    }

    override fun failure(message: String) {
        Toast.makeText(context, "Error favorite $message", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        dataAdapterHandler()
    }
}