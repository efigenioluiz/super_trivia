package com.ifpr.supertrivia.network.service

import com.ifpr.supertrivia.model.game.GameCallBack
import com.ifpr.supertrivia.model.question.QuestionCallBack
import com.ifpr.supertrivia.model.question.verify.VerifyCallBack
import com.ifpr.supertrivia.model.question.verify.VerifyData
import retrofit2.Call
import retrofit2.http.*

interface QuestionService {
    @GET("/problems/next")
    fun nextQuestion(@Header("Authorization") token : String): Call<QuestionCallBack>


    @GET("/problems/view")
    fun existQuestion(@Header("Authorization") token : String): Call<QuestionCallBack>

    @POST("/problems/answer?")
    fun answerQuestion(@Header("Authorization") token: String, @Query("answer") answer: Int): Call<VerifyCallBack>


}