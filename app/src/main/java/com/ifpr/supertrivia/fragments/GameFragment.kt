package com.ifpr.supertrivia.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ifpr.supertrivia.R
import com.ifpr.supertrivia.dao.GameDAO
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.android.synthetic.main.fragment_game.view.*

class GameFragment : Fragment() {
    init {
        btRandomPlay.visibility = View.GONE


//        if (withSetup!!) view.btRandomPlay.visibility = View.GONE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_game, container, false)

        val sharedPref = activity?.getSharedPreferences("user", Context.MODE_PRIVATE)


        val dao = GameDAO()
        val token = sharedPref?.getString("token", "")

        val withSetup = arguments?.getBoolean("withSetup")




        view.btNext.setOnClickListener {nextToQuestion()}
        return view
    }

    private fun nextToQuestion() {
        TODO("Not yet implemented")
    }

//    1. Gson gson = new Gson();
//    2. Setting sets = gson.fromJson(json, Setting.class);


}