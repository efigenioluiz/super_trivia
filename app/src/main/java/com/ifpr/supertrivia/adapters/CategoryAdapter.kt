package com.ifpr.supertrivia.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifpr.supertrivia.R
import com.ifpr.supertrivia.dao.CategoryDAO
import com.ifpr.supertrivia.model.Category

import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter() : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    private val dao = CategoryDAO()
    private var categories = mutableListOf<Category>()

    init {
        dao.getAll {
            categories = it.toMutableList()
            notifyDataSetChanged()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
        // retorno o layout R.layout....
        R.layout.item_category
    }

    override fun getItemCount() = categories.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHolder =
        ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(viewType, parent, false)
        )


    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        if (categories.isNotEmpty()) {
            val category = categories[position]
            holder.fillView(category)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun fillView(category: Category) {
            itemView.txtCategory.text = category.name
        }

    }
}
