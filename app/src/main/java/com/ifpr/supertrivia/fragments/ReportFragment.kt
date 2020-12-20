package com.ifpr.supertrivia.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.ifpr.supertrivia.R
import com.ifpr.supertrivia.model.game.endgame.EndGameData
import kotlinx.android.synthetic.main.fragment_report.view.*
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


class ReportFragment : Fragment() {

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_report, container, false)
        val gson = Gson()

        val game = arguments?.getString("endGame")

        val endGame = gson.fromJson(game, EndGameData::class.java)


        val s = ZonedDateTime.parse(endGame.game.started_at)
        val f = ZonedDateTime.parse(endGame.game.finished_at)


        val formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm")
        val startedAt = s.format(formatter)
        val finishedAt = f.format(formatter)

        view.txtStartedAt.text = getString(R.string.started)+" "+startedAt
        view.txtFineshedAt.text = getString(R.string.finished)+" "+finishedAt
        view.txtEndScore.text = getString(R.string.endScore)+endGame.game.score.toString()


        return view
    }
}