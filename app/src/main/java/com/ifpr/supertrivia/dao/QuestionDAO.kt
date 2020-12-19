package com.ifpr.supertrivia.dao

import android.util.Log
import android.widget.Toast
import com.ifpr.supertrivia.MainActivity
import com.ifpr.supertrivia.model.question.Question
import com.ifpr.supertrivia.model.question.QuestionCallBack
import com.ifpr.supertrivia.model.question.QuestionData
import com.ifpr.supertrivia.network.service.GameService
import com.ifpr.supertrivia.network.service.QuestionService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.AccessController.getContext

class QuestionDAO {
    val url = "https://super-trivia-server.herokuapp.com/"
    val retrofit = Retrofit.Builder()
        .baseUrl(url).addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(QuestionService::class.java)

    fun nextQuestion(token: String, finished: (QuestionData) -> Unit) {

        service.nextQuestion(token).enqueue(object : Callback<QuestionCallBack> {
            override fun onResponse(
                call: Call<QuestionCallBack>,
                response: Response<QuestionCallBack>
            ) {

                if (!response.isSuccessful) {
                    Log.e("question", response.body().toString())

                } else {
                    val question = response.body()!!

                    Log.e("question", response.body()!!.status)
                    Log.e("array", response.body()!!.data?.answers.toString())
                    finished(question.data!!)
                }
            }

            override fun onFailure(call: Call<QuestionCallBack>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })
    }
}