package com.ifpr.supertrivia.fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.ifpr.supertrivia.R
import com.ifpr.supertrivia.adapters.AnswerAdapter
import com.ifpr.supertrivia.dao.GameDAO
import com.ifpr.supertrivia.dao.QuestionDAO
import com.ifpr.supertrivia.model.question.Question
import com.ifpr.supertrivia.model.question.QuestionData
import kotlinx.android.synthetic.main.fragment_choose_level.view.*
import kotlinx.android.synthetic.main.fragment_game.view.*

class GameFragment : Fragment() {

    lateinit var answerAdapter: AnswerAdapter
    var dao = GameDAO()
    var daoQ = QuestionDAO()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_game, container, false)


        val sharedPref = activity?.getSharedPreferences("user", Context.MODE_PRIVATE)

        val token = sharedPref?.getString("token", "")
        val withSetup = arguments?.getBoolean("withSetup")

        if (token != null && withSetup == true) {

            val gson = Gson()

            val questionJson = arguments?.getString("question")

            val question = gson.fromJson(questionJson, QuestionData::class.java)



            if (question != null) {
                answerAdapter = AnswerAdapter()

                answerAdapter.setAnswers(question.problem.answers)

                view.rcAnswer.adapter = answerAdapter

                view.rcAnswer.layoutManager =
                    LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)


                view.txtQuestion.text = question.problem.question

            }


        }else {
            val build: AlertDialog.Builder = AlertDialog.Builder(activity)
            build.setView(R.layout.screen_load)
            build.setCancelable(false)

            val alertDialog: AlertDialog = build.create()
            alertDialog.show()

            var question: QuestionData

            if (token != null) {
                daoQ.existQuestion(token) {

                    alertDialog.dismiss()

                    question = it
                    answerAdapter = AnswerAdapter()

                    answerAdapter.setAnswers(question.problem.answers)

                    view.rcAnswer.adapter = answerAdapter

                    view.rcAnswer.layoutManager =
                        LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)


                    view.txtQuestion.text = question.problem.question

                }
            }

        }
        view.btNext.setOnClickListener { nextToQuestion() }


        return view
    }

    private fun nextToQuestion() {
        TODO("Not yet implemented")
    }

}