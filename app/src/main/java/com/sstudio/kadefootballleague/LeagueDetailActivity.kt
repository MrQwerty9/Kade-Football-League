package com.sstudio.kadefootballleague

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import org.jetbrains.anko.*

class LeagueDetailActivity : AppCompatActivity() {
    companion object {
        const val photo = 1
        const val nameLeague = 2
        const val description = 3
        const val POSITIONEXTRA = "position_extra"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataLeague = intent.getParcelableExtra<League>(POSITIONEXTRA)
        DetailClubSepakBolaUI(dataLeague).setContentView(this)
    }

    internal class DetailClubSepakBolaUI(private var list: League) : AnkoComponent<LeagueDetailActivity> {
        override fun createView(ui: AnkoContext<LeagueDetailActivity>) = with(ui){
            relativeLayout {
                imageView {
                    id = photo
                    Glide.with(this).load(list.photo).into(this)
                    //  setImageResource(R.drawable.img_barca)
                }.lparams(width = dip(150), height = dip(150)){
                    topMargin = dip(15)
                    alignParentTop()
                    centerHorizontally()
                }
                textView {
                    id = nameLeague
                    text = list.name
                    textSize = 18f
                    textColor = Color.BLACK
                }.lparams(width = wrapContent, height = wrapContent) {
                    topMargin = dip(165)
                    alignParentTop()
                    centerHorizontally()
                }
                textView {
                    id = description
                    text = list.description
                }.lparams(width = wrapContent, height = wrapContent) {
                    alignParentTop()
                    centerHorizontally()
                    topMargin = dip(195)
                    leftMargin = dip(15)
                    rightMargin = dip(10)
                }
            }
        }
    }
}
