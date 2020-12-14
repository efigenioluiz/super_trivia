package com.ifpr.supertrivia.network.service

import com.ifpr.supertrivia.model.Category
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CategotyService {

    @GET("/categories")
    fun getAll(): Call<List<Category>>

}