package com.ifpr.supertrivia.network.service

import com.ifpr.supertrivia.model.game.GameCallBack
import com.ifpr.supertrivia.model.question.QuestionCallBack
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface QuestionService {
    @GET("/problems/next")
    @Headers("charset: utf-8")
    fun nextQuestion(@Header("Authorization") token : String): Call<QuestionCallBack>

    @GET("/problems/view")
    @Headers("charset: utf-8")
    fun existQuestion(@Header("Authorization") token : String): Call<QuestionCallBack>


}