package com.ifpr.supertrivia.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifpr.supertrivia.R
import com.ifpr.supertrivia.dao.CategoryDAO
import com.ifpr.supertrivia.model.category.Category

import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter() : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    private val dao = CategoryDAO()
    private var categories = listOf<Category>()

    init {
        dao.getAll {
            categories = it
            notifyDataSetChanged()
        }
    }

    override fun getItemViewType(position: Int)= R.layout.item_category


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
