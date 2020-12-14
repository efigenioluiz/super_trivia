package com.ifpr.supertrivia.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifpr.supertrivia.R
import com.ifpr.supertrivia.adapters.CategoryAdapter
import kotlinx.android.synthetic.main.fragment_choose_level.view.*

class ChooseLevelFragment : Fragment() {

    lateinit var adapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_choose_level, container, false)

        adapter = CategoryAdapter()

        view.recyclerView.adapter = adapter
        view.recyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        view.progress.max = 3
        view.progress.min = 1

        return view
    }

}