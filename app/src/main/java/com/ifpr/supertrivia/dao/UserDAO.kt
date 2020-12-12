package com.ifpr.supertrivia.dao

import com.ifpr.supertrivia.model.User
import com.ifpr.supertrivia.network.service.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserDAO {
    val url = "https://super-trivia-server.herokuapp.com/"
    val retrofit = Retrofit.Builder()
        .baseUrl(url).addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(UserService::class.java)

    fun getAll(finished: (users: List<User>) -> Unit) {

        service.getAll().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {

                val users = response.body()!!
                finished(users)
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })
    }

    fun login(username: String,password: String, finished: (User) -> Unit) {
        service.login(username,password).enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if(response.body() != null){

                    if(response.body()!!.isNotEmpty()) {
                        val user = response.body()!!.first()
                        finished(user)
                    }

                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })
    }
    fun insert(user: User, finished: (User) -> Unit) {
        service.insert(user ).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                val user = response.body()!!
                finished(user)
            }
            override fun onFailure(call: Call<User>, t: Throwable) { }
        })
    }

    fun update(user: User, finished: ( User) -> Unit) {
        service.update(user.id!!, user).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                val user = response.body()!!
                finished(user)
            }
            override fun onFailure(call: Call<User>, t: Throwable) { }
        })
    }

    fun get(id: Long, finished: (user: User) -> Unit) {
        service.getUser(id).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                val user = response.body()!!
                finished(user)
            }
            override fun onFailure(call: Call<User>, t: Throwable) { }
        })
    }
}