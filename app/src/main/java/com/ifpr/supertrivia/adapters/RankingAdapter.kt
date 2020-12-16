package com.ifpr.supertrivia.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifpr.supertrivia.R
import com.ifpr.supertrivia.dao.RankingDAO
import com.ifpr.supertrivia.model.ranking.RankingUser
import kotlinx.android.synthetic.main.item_category.view.*
import kotlinx.android.synthetic.main.item_ranking.view.*

class RankingAdapter() : RecyclerView.Adapter<RankingAdapter.ViewHolder>() {


    private val dao = RankingDAO()
    private var rankings = listOf<RankingUser>()

    init {
        dao.getAll {
            rankings = it

            Log.e("jsonapi", rankings.toString())
            notifyDataSetChanged()
        }
    }

    override fun getItemCount() = rankings.size

    override fun getItemViewType(position: Int): Int = R.layout.item_ranking

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingAdapter.ViewHolder =
        ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(viewType, parent, false)
        )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (rankings.isNotEmpty()) {
            val rankingsUser = rankings[position]
            holder.fillView(rankingsUser)
        }
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun fillView(rankingUser: RankingUser) {
            itemView.txtUser.text = rankingUser.user
            itemView.txtScore.text = rankingUser.score

        }

    }
}
