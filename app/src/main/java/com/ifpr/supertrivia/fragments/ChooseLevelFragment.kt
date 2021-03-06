package com.ifpr.supertrivia.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.ifpr.supertrivia.AcessActivity
import com.ifpr.supertrivia.R
import com.ifpr.supertrivia.adapters.CategoryAdapter
import com.ifpr.supertrivia.dao.CategoryDAO
import com.ifpr.supertrivia.dao.GameDAO
import com.ifpr.supertrivia.dao.QuestionDAO
import com.ifpr.supertrivia.model.Difficulty
import com.ifpr.supertrivia.model.category.Category
import kotlinx.android.synthetic.main.fragment_choose_level.view.*
import java.lang.Integer.parseInt


class ChooseLevelFragment : Fragment() {

    lateinit var adapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_choose_level, container, false)


        val dao = CategoryDAO()
        adapter = CategoryAdapter()

        view.recyclerView.adapter = adapter

        view.recyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

//        view.progress
        view.progress.max = 3
        view.progress.min = 1



        view.progress.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                Log.i("TAG", progress.toString())
            }
        })

        view.btPlay.setOnClickListener {
            setSetup(
                adapter.getCategory(),
                parseInt(view.progress.progress.toString())
            )
        }
        view.btRandom.setOnClickListener {
            random()
        }
        view.btLogout.setOnClickListener { logout()}
        return view
    }

    @SuppressLint("ResourceType")
    private fun setSetup(category: Category?, lvl: Int) {
        val daoGame = GameDAO()
        val daoQuestion = QuestionDAO()


        if (category != null) {
            val difficulty = Difficulty(lvl)

            val sharedPref = activity?.getSharedPreferences("user", Context.MODE_PRIVATE)
            val token = sharedPref?.getString("token", "")

            if (token != null) {

                val build: AlertDialog.Builder = AlertDialog.Builder(activity)
                build.setView(R.layout.screen_load)

                build.setCancelable(false)

                val alertDialog: AlertDialog = build.create()
                alertDialog.show()

                daoGame.startGameWhitSetup(token, difficulty.difficulty, category.id) {
                    Log.i("DF", difficulty.difficulty)
                    val bundle = Bundle()

                    bundle.putInt("score",it.score)

                    daoQuestion.nextQuestion(token) {


                        val gson = Gson()
                        val questionJson = gson.toJson(it)

                        bundle.putBoolean("withSetup", true)
                        bundle.putString("question", questionJson)

                        alertDialog.dismiss()
                        findNavController().navigate(R.id.gameFragment, bundle)
                    }
                    daoQuestion.existQuestion(token) {
                        alertDialog.dismiss()
                        Toast.makeText(activity,getString(R.string.game_progress),Toast.LENGTH_SHORT).show()
                    }

                }

            }
        } else {
            Toast.makeText(activity, getString(R.string.erro_select_category), Toast.LENGTH_SHORT)
                .show()
        }
    }
    private fun random(){
        val daoGame = GameDAO()
        val daoQuestion = QuestionDAO()


        val sharedPref = activity?.getSharedPreferences("user", Context.MODE_PRIVATE)
        val token = sharedPref?.getString("token", "")

        if (token != null) {
            val build: AlertDialog.Builder = AlertDialog.Builder(activity)
            build.setView(R.layout.screen_load)

            build.setCancelable(false)

            val alertDialog: AlertDialog = build.create()
            alertDialog.show()

            daoGame.startGame(token){
                val bundle = Bundle()

                bundle.putInt("score",it.score)

                daoQuestion.nextQuestion(token) {
                    alertDialog.dismiss()

                    val gson = Gson()
                    val questionJson = gson.toJson(it)

                    bundle.putBoolean("withSetup", true)
                    bundle.putString("question", questionJson)

                    findNavController().navigate(R.id.gameFragment, bundle)
                }
            }
            daoQuestion.existQuestion(token) {
                alertDialog.dismiss()
                Toast.makeText(activity,getString(R.string.game_progress),Toast.LENGTH_SHORT).show()
            }


        }
    }
    @SuppressLint("CommitPrefEdits")
    private fun logout() {
        val sharedPref = activity?.getSharedPreferences("user", Context.MODE_PRIVATE)

        sharedPref?.edit()?.putString("password", "")?.putString("email", "")?.putString("token", "")
            ?.apply()


        val intent = Intent(activity, AcessActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

}