package com.ifpr.supertrivia.fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.ifpr.supertrivia.R
import com.ifpr.supertrivia.adapters.AnswerAdapter
import com.ifpr.supertrivia.dao.GameDAO
import com.ifpr.supertrivia.dao.QuestionDAO
import com.ifpr.supertrivia.model.category.Category
import com.ifpr.supertrivia.model.question.Answer
import com.ifpr.supertrivia.model.question.QuestionData
import kotlinx.android.synthetic.main.fragment_game.view.*
import kotlinx.android.synthetic.main.screen_start_game.*

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

        val score = arguments?.getInt("score", 0)

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


                view.txtQuestion.text =
                    question.problem.question.replace("&quot;", " ' ").replace("&#039;", " ' ")
//                view.Score.text = score.toString()

            }


        } else {

            val build: AlertDialog.Builder = AlertDialog.Builder(activity)
            build.setView(R.layout.screen_start_game)
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


                    view.txtQuestion.text =
                        question.problem.question.replace("&quot;", " ' ").replace("&#039;", " ' ")

                }
                alertDialog.btStart.setOnClickListener {
                    alertDialog.dismiss()
                    findNavController().navigate(R.id.chooseLevelFragment)
                }

            }

        }
        view.btNext.setOnClickListener {
            nextToQuestion(
                token,
                answerAdapter.getAnswer(),
            )
        }


        return view
    }

    private fun nextToQuestion(token: String?, answer: Answer?) {

        val bundle = Bundle()
        if (answer != null && token != null) {

            val build: AlertDialog.Builder = AlertDialog.Builder(activity)
            val buildII: AlertDialog.Builder = AlertDialog.Builder(activity)
            build.setView(R.layout.screen_load)
            build.setCancelable(false)


            buildII.setPositiveButton(getString(R.string.next)) { dialog, _ ->

                daoQ.nextQuestion(token) {

                    val gson = Gson()
                    val questionJson = gson.toJson(it)

                    bundle.putBoolean("withSetup", true)
                    bundle.putString("question", questionJson)


                    findNavController().navigate(R.id.gameFragment, bundle)

                    dialog.dismiss()
                }

            }
            buildII.setNegativeButton(getString(R.string.endgame)) { dialog, _ ->


                dao.endGame(token) {


                    val gson = Gson()
                    val endGame = gson.toJson(it)
                    bundle.putString("endGame", endGame)

                    findNavController().navigate(R.id.reportFragment, bundle)
                }
                dialog.dismiss()
            }


            val alertDialog: AlertDialog = build.create()
            val showUser: AlertDialog = buildII.create()

            alertDialog.show()

            daoQ.answerQuestion(answer.order, token) {
                alertDialog.dismiss()

                if (it.answer.status == "incorrect") {
                    showUser.setTitle(getString(R.string.incorrect))
                } else {
                    showUser.setTitle(getString(R.string.correct))
                }
                showUser.setMessage(getString(R.string.your_score) + " " + it.answer.score.toString())

                showUser.setCancelable(false)
                showUser.show()

                Log.i("answer", it.answer.correct_answer.toString())
                Log.i("answer", it.answer.status)


            }
        } else {
            Toast.makeText(activity, getString(R.string.select_your_answer), Toast.LENGTH_SHORT)
                .show()
        }
    }


}