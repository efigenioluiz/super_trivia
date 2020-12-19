package com.ifpr.supertrivia.fragments

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
import com.ifpr.supertrivia.model.question.Question
import com.ifpr.supertrivia.model.question.QuestionData
import kotlinx.android.synthetic.main.fragment_choose_level.view.*
import kotlinx.android.synthetic.main.fragment_game.view.*

class GameFragment : Fragment() {

    lateinit var answerAdapter : AnswerAdapter
    var dao = GameDAO()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_game, container, false)

        val sharedPref = activity?.getSharedPreferences("user", Context.MODE_PRIVATE)


        val token = sharedPref?.getString("token", "")

        val gson = Gson()
        val withSetup = arguments?.getBoolean("withSetup")
        val questionJson = arguments?.getString("question")

        val question = gson.fromJson(questionJson, QuestionData::class.java)


        if (question != null) {
            answerAdapter = AnswerAdapter()

//            answerAdapter.setAnswers(question.answers)
//            Log.i("list", question.answers.toString())

            view.rcAnswer.adapter = answerAdapter

            view.rcAnswer.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)


//            Log.i("answer", question.answers.toString())
            view.txtQuestion.text = question.problem.question

        }


        view.btNext.setOnClickListener { nextToQuestion() }
        return view
    }

    private fun nextToQuestion() {
        TODO("Not yet implemented")
    }

//    1. val gson =  Gson()
//    2 get bundle
//    3. quesiton  = gson.fromJson(questionJson, Setting.class)


}