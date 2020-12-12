package com.ifpr.supertrivia.network.service
import com.ifpr.supertrivia.model.User
import retrofit2.Call
import retrofit2.http.*


interface UserService {
    @GET("/users")
    fun getAll(): Call<List<User>>

    @GET("/users/{id}")
    fun getUser(@Path("id") id: Long): Call<User>


    @POST("users")
    @Headers("Content-Type: application/json")
    fun insert(@Body user: User): Call<User>

    @DELETE("users/{id}")
    fun delete(@Path("id") id: Long): Call<Void>

    @GET("users?")
    fun login(@Query("username") username: String, @Query("password") password: String): Call<List<User>>

    @PATCH("users/{id}")
    @Headers("Content-Type: application/json")
    fun update(@Path("id") id: Long, @Body person: User): Call<User>
}