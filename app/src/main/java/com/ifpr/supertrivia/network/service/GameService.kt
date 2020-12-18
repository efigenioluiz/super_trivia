package com.ifpr.supertrivia.network.service

import com.ifpr.supertrivia.model.game.GameCallBack
import com.ifpr.supertrivia.model.ranking.RankingCallBack
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface GameService {
    @GET("/games")
    fun startGame(@Header("Authorization") token : String): Call<GameCallBack>

    @DELETE("/games")
    fun endGame(@Header("Authorization") token : String)
}