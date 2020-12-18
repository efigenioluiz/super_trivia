package com.ifpr.supertrivia.dao

import com.ifpr.supertrivia.model.question.Question
import com.ifpr.supertrivia.model.question.QuestionCallBack
import com.ifpr.supertrivia.network.service.GameService
import com.ifpr.supertrivia.network.service.QuestionService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuestionDAO {
    val url = "https://super-trivia-server.herokuapp.com/"
    val retrofit = Retrofit.Builder()
        .baseUrl(url).addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(QuestionService::class.java)

    fun nextQuestion(token: String,finished: (Question) -> Unit) {

        service.nextQuestion(token).enqueue(object : Callback<QuestionCallBack> {
            override fun onResponse(call: Call<QuestionCallBack>, response: Response<QuestionCallBack>) {

                val question = response.body()!!
                finished(question.data)
            }

            override fun onFailure(call: Call<QuestionCallBack>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })
    }
}