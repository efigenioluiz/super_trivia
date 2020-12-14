package com.ifpr.supertrivia.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifpr.supertrivia.model.Category

class CategoryAdapter():RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
//    private val dao

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
        // retorno o layout R.layout....
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }
}
