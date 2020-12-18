package com.ifpr.supertrivia.network.service

import com.ifpr.supertrivia.model.game.GameCallBack
import retrofit2.Call
import retrofit2.http.*

interface GameService {
    @GET("/games")
    fun startGame(@Header("Authorization") token : String): Call<GameCallBack>

    @GET("/games?")
    fun startGameWithSetup(@Header("Authorization") token: String,
                           @Query("difficulty") difficulty: String,
                           @Query("category_id") category_id: Long?
    ): Call<GameCallBack>

    @DELETE("/games")
    fun endGame(@Header("Authorization") token : String)
}