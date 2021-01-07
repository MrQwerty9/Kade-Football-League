package com.sstudio.kadefootballleague.ui.home

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sstudio.kadefootballleague.R
import com.sstudio.kadefootballleague.ui.favorite.MatchFavoriteFragment
import com.sstudio.kadefootballleague.ui.league.LeagueFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setSupportActionBar(toolbar)
        toolbar.title = this.getString(R.string.app_name)
        bottom_nav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        if (savedInstanceState == null){
            bottom_nav.selectedItemId = R.id.navigation_league
        }
    }

    private val mOnNavigationItemSelectedListener =
        object : BottomNavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                val fragment: Fragment
                when (item.itemId){
                    R.id.navigation_league -> {
                        fragment = LeagueFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(
                                R.id.layout_container, fragment, fragment::class.java.simpleName
                            ).commit()
                        return true
                    }
                    R.id.navigation_favorite -> {
                        fragment = MatchFavoriteFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(
                                R.id.layout_container, fragment, fragment::class.java.simpleName
                            ).commit()
                        return true
                    }
                }
                return false
            }
        }
}