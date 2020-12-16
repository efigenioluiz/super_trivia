package com.ifpr.supertrivia.dao

import android.util.Log
import com.ifpr.supertrivia.model.game.Game
import com.ifpr.supertrivia.model.game.GameCallBack
import com.ifpr.supertrivia.model.ranking.RankingCallBack
import com.ifpr.supertrivia.model.ranking.RankingUser
import com.ifpr.supertrivia.network.service.GameService
import com.ifpr.supertrivia.network.service.RankingService
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

    fun getGame(finished: ((Game)) -> Unit) {

        service.getGame().enqueue(object : Callback<GameCallBack> {
            override fun onResponse(call: Call<GameCallBack>, response: Response<GameCallBack>) {

                if (!response.isSuccessful) {
                    Log.e("jsonapi", response.body().toString())
                } else {
                    val game = response.body()!!

                    Log.e("jsonapi", game.toString())

                    finished(game.data.game)
                }

            }

            override fun onFailure(call: Call<GameCallBack>, t: Throwable) {
                Log.e("jsonapi", t.toString())
            }


        })
    }
}