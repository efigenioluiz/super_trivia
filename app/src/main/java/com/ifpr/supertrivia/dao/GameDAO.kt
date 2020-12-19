package com.ifpr.supertrivia.dao

import android.util.Log
import com.ifpr.supertrivia.model.game.Game
import com.ifpr.supertrivia.model.game.GameCallBack
import com.ifpr.supertrivia.model.game.endgame.EndGameCallBack
import com.ifpr.supertrivia.model.game.endgame.EndGameData
import com.ifpr.supertrivia.network.service.GameService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GameDAO {
    val url = "https://super-trivia-server.herokuapp.com/"
    val retrofit = Retrofit.Builder()
        .baseUrl(url).addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(GameService::class.java)

    fun startGame(token: String, finished: ((Game)) -> Unit) {

        service.startGame(token).enqueue(object : Callback<GameCallBack> {
            override fun onResponse(call: Call<GameCallBack>, response: Response<GameCallBack>) {

                if (!response.isSuccessful) {
                    Log.e("aqui", response.body().toString())
                } else {
                    val game = response.body()!!

                    if(game.status == "success"){
                        //starting game
                        Log.i("game",game.status )

                        finished(game.data.game)
                    }else{
                        //gaming in progress
                        //finished(game.data.game)
                    }

                }

            }

            override fun onFailure(call: Call<GameCallBack>, t: Throwable) {
                Log.e("jsonapi", t.toString())
            }


        })
    }
    fun startGameWhitSetup(token: String, difficulty:String, category_id: Long?, finished: ((Game)) -> Unit) {

        service.startGameWithSetup(token,difficulty,category_id).enqueue(object : Callback<GameCallBack> {
            override fun onResponse(call: Call<GameCallBack>, response: Response<GameCallBack>) {

                if (!response.isSuccessful) {
                    Log.e("aqui", response.body().toString())
                } else {
                    val game = response.body()!!

                    if(game.status == "success"){
                        //starting game
                        Log.i("game",game.status )

                        finished(game.data.game)
                    }else{
                        //gaming in progress
                        //finished(game.data.game)
                    }

                }

            }

            override fun onFailure(call: Call<GameCallBack>, t: Throwable) {
                Log.e("jsonapi", t.toString())
            }


        })
    }
    fun endGame(token: String, finished: ((EndGameData)) -> Unit) {

        service.endGame(token).enqueue(object : Callback<EndGameCallBack> {
            override fun onResponse(call: Call<EndGameCallBack>, response: Response<EndGameCallBack>) {

                if (!response.isSuccessful) {
                    Log.e("aqui", response.body().toString())
                } else {
                    val end = response.body()!!

                    finished(end.data)


                }

            }

            override fun onFailure(call: Call<EndGameCallBack>, t: Throwable) {
                Log.e("jsonapi", t.toString())
            }


        })
    }

}