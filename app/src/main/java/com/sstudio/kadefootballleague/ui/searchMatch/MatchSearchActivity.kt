package com.sstudio.kadefootballleague.ui.searchMatch

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.sstudio.kadefootballleague.R
import com.sstudio.kadefootballleague.data.remote.api.ApiConfig
import com.sstudio.kadefootballleague.model.Match
import com.sstudio.kadefootballleague.ui.leagueDetail.MatchAdapter
import kotlinx.android.synthetic.main.activity_match_search.*
import org.jetbrains.anko.toast

class MatchSearchActivity : AppCompatActivity(), MatchSearchView {

    private lateinit var matchSearchPresenter: MatchSearchPresenter
    private lateinit var matchAdapter: MatchAdapter
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_search)

        toolbar.title = getString(R.string.search)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        matchSearchPresenter = MatchSearchPresenter(this, ApiConfig.getApiService())
        matchAdapter = MatchAdapter()

        with(rv_match_search){
            layoutManager = LinearLayoutManager(this@MatchSearchActivity)
            setHasFixedSize(true)
            adapter = matchAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager?
        if (searchManager != null) {
            val searchView = menu?.findItem(R.id.search)?.actionView as SearchView
            searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
            searchView.queryHint = resources.getString(R.string.search)
            searchView.onActionViewExpanded()
            menu.findItem(R.id.search).expandActionView()
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String): Boolean {
                    progress_bar.visibility = View.VISIBLE
                    matchSearchPresenter.getMatchSearch(query)
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    handler = Handler(Looper.getMainLooper())
                    if (newText.isNotEmpty()) {
                        progress_bar.visibility = View.VISIBLE
                        handler.removeCallbacksAndMessages(null)
                        handler.postDelayed(Runnable {
                            matchSearchPresenter.getMatchSearch(newText)
                        }, 300)
                    }
                    return true
                }
            })
        }
        return true
    }

    override fun showMatchFound(data: List<Match>) {
        progress_bar.visibility = View.GONE
        matchAdapter.setMatch(data)
    }

    override fun failureResponse(message: String) {
        toast("Error $message")
    }
}