package com.ifpr.supertrivia.network.service

import com.ifpr.supertrivia.model.game.GameCallBack
import com.ifpr.supertrivia.model.question.QuestionCallBack
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface QuestionService {
    @GET("/problems/next")
    fun nextQuestion(@Header("Authorization") token : String): Call<QuestionCallBack>
}