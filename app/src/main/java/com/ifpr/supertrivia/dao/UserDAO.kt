package com.ifpr.supertrivia.dao

import android.util.Log
import com.ifpr.supertrivia.model.user.User
import com.ifpr.supertrivia.model.user.UserCallback
import com.ifpr.supertrivia.model.user.UserInput
import com.ifpr.supertrivia.model.user.UserLogin
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

    fun login(userLogin: UserLogin, finished: (User) -> Unit) {
        service.login(userLogin).enqueue(object : Callback<UserCallback> {
            override fun onResponse(call: Call<UserCallback>, response: Response<UserCallback>) {
                if(response.body() != null){

                    if(response.isSuccessful) {
                        val user = response.body()!!
                        finished(user.data.user!!)
                    }

                }
            }

            override fun onFailure(call: Call<UserCallback>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })
    }
    fun insert(userInput: UserInput, finished: (User) -> Unit) {
        service.insert(userInput ).enqueue(object : Callback<UserCallback> {
            override fun onResponse(call: Call<UserCallback>, response: Response<UserCallback>) {

                if(!response.isSuccessful){
                    Log.e("tag", response.code().toString())

                }else{
                    val user = response.body()!!
                    Log.e("tag", response.code().toString())


                    Log.e("tag", user.status)
                    finished(user.data.user!!)
                }

            }
            override fun onFailure(call: Call<UserCallback>, t: Throwable) {
                Log.e("failure", t.toString())
                Log.e("failure", call.toString())
            }
        })
    }

    fun update(user: User, finished: (User) -> Unit) {
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