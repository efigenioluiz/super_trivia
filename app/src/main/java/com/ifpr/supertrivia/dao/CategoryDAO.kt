package com.ifpr.supertrivia.dao

import android.util.Log
import com.ifpr.supertrivia.model.Category
import com.ifpr.supertrivia.network.service.CategotyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CategoryDAO {
    val url = "https://super-trivia-server.herokuapp.com/"
    val retrofit = Retrofit.Builder()
        .baseUrl(url).addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(CategotyService::class.java)

    fun getAll(finished: (category: List<Category>) -> Unit) {

        service.getAll().enqueue(object : Callback<List<Category>> {
            override fun onResponse(call: Call<List<Category>>, response: Response<List<Category>>) {

                val category = response.body()!!
                Log.e("jsonapi", category.toString())
//                Log.i("api", category[1].toString())
                finished(category)
                // return just data
            }

            override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                Log.e("jsonapi",t.toString())
            }


        })
    }
}