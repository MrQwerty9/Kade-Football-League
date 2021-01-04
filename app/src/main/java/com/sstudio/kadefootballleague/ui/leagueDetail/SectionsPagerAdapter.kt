package com.sstudio.kadefootballleague.ui.leagueDetail

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sstudio.kadefootballleague.R
import com.sstudio.kadefootballleague.ui.lastMatch.LastMatchFragment
import com.sstudio.kadefootballleague.ui.nextMatch.NextMatchFragment

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
       const val PAGE_COUNT = 2
    }

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null

        when (position) {
            0 -> fragment = LastMatchFragment()
            1 -> fragment = NextMatchFragment()
        }

        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return context.resources.getString(R.string.tab_LastMatch)
            1 -> return context.resources.getString(R.string.tab_NextMatch)
        }

        return null
    }

    override fun getCount(): Int {
        return PAGE_COUNT
    }
}