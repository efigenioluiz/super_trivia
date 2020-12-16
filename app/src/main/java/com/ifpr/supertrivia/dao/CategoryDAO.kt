package com.ifpr.supertrivia.dao

import android.util.Log
import com.ifpr.supertrivia.model.category.Category
import com.ifpr.supertrivia.model.category.CategoryCallBack
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

        service.getAll().enqueue(object : Callback<CategoryCallBack> {
            override fun onResponse(call: Call<CategoryCallBack>, callBack: Response<CategoryCallBack>) {

                if(!callBack.isSuccessful){
                    Log.e("jsonapi", callBack.body().toString())
                }else{
                    val categories = callBack.body()!!
                    Log.e("jsonapi", categories.toString())

                    categories.data?.let { finished(it.categories) }
                }

            }

            override fun onFailure(call: Call<CategoryCallBack>, t: Throwable) {
                Log.e("jsonapi",t.toString())
            }


        })
    }
}