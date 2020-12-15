package com.ifpr.supertrivia.network.service

import com.ifpr.supertrivia.model.category.CategoryCallBack
import retrofit2.Call
import retrofit2.http.GET

interface CategotyService {

    @GET("/categories")
    fun getAll(): Call<CategoryCallBack>

}