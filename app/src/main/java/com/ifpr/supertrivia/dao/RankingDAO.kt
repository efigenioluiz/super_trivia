package com.ifpr.supertrivia.dao

import android.util.Log
import com.ifpr.supertrivia.model.category.Category
import com.ifpr.supertrivia.model.category.CategoryCallBack
import com.ifpr.supertrivia.model.ranking.RankingCallBack
import com.ifpr.supertrivia.model.ranking.RankingUser
import com.ifpr.supertrivia.network.service.CategotyService
import com.ifpr.supertrivia.network.service.RankingService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RankingDAO {
    val url = "https://super-trivia-server.herokuapp.com/"
    val retrofit = Retrofit.Builder()
        .baseUrl(url).addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(RankingService::class.java)

    fun getAll(finished: (category: List<RankingUser>) -> Unit) {

        service.getAll().enqueue(object : Callback<RankingCallBack> {
            override fun onResponse(call: Call<RankingCallBack>, callBack: Response<RankingCallBack>) {

                if(!callBack.isSuccessful){
                    Log.e("jsonapi", callBack.body().toString())
                }else{
                    val rankingUser = callBack.body()!!

                    Log.e("jsonapi", rankingUser.toString())

                    finished(rankingUser.data.ranking)
                }

            }

            override fun onFailure(call: Call<RankingCallBack>, t: Throwable) {
                Log.e("jsonapi",t.toString())
            }


        })
    }
}