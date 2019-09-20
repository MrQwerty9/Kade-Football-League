package com.sstudio.kadefootballleague

import android.view.ViewGroup
import android.widget.LinearLayout
import org.jetbrains.anko.*

class LeagueUI : AnkoComponent<ViewGroup> {
    companion object {
        const val photo = 1
        const val nameLeague = 2
    }

    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        linearLayout {
            orientation = LinearLayout.HORIZONTAL
            padding = dip(16)

            imageView {
                id = photo
            }.lparams(width = dip(50), height = dip(50))
            textView {
                id = nameLeague
            }.lparams(width = wrapContent, height = wrapContent) {
                margin = dip(10)
            }
        }
    }


}