package com.ifpr.supertrivia.network.service

import com.ifpr.supertrivia.model.ranking.RankingCallBack
import retrofit2.Call
import retrofit2.http.GET

interface RankingService {

    @GET("/ranking")
    fun getAll(): Call<RankingCallBack>
}