package com.ifpr.supertrivia.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.ifpr.supertrivia.R
import com.ifpr.supertrivia.model.game.endgame.EndGameData
import com.ifpr.supertrivia.model.question.QuestionData

class ReportFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_report, container, false)
        val gson = Gson()

        val game = arguments?.getString("endGame")

        val endGame = gson.fromJson(game, EndGameData::class.java)



        return view
    }
}