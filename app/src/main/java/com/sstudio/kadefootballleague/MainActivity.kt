package com.sstudio.kadefootballleague

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {

    private var dataitem : MutableList<League> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)
        initData()

        verticalLayout {
            lparams(matchParent, matchParent)
            orientation = LinearLayout.VERTICAL

            recyclerView {
                lparams(matchParent, matchParent)
                layoutManager = LinearLayoutManager(context)
                adapter = LeagueAdapter(dataitem) {
                    startActivity<LeagueDetailActivity>(LeagueDetailActivity.POSITIONEXTRA to it)
                }
            }
        }
    }

    private fun initData(){
        val nameLeague = resources.getStringArray(R.array.name)
        val photo = resources.obtainTypedArray(R.array.photo)
        val description = resources.getStringArray(R.array.description)

        dataitem.clear()

        for (i in nameLeague.indices){
            dataitem.add(League(nameLeague[i], description[i], photo.getResourceId(i,0)))
        }
        photo.recycle()
    }
}

class MainActivityUI : AnkoComponent<MainActivity> {
    companion object {
        const val recyclerLeague = 1
    }
    override fun createView(ui: AnkoContext<MainActivity>): View = with(ui)  {
        relativeLayout {
            recyclerView {
                id = recyclerLeague
            }.lparams(width = matchParent, height = matchParent)
        }
    }
}