package com.ifpr.supertrivia.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.ifpr.supertrivia.MainActivity
import com.ifpr.supertrivia.R
import com.ifpr.supertrivia.dao.UserDAO
import com.ifpr.supertrivia.model.user.UserInput
import com.ifpr.supertrivia.model.user.UserLogin
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val sharedPref = activity?.getSharedPreferences("user", Context.MODE_PRIVATE)

        if (sharedPref != null) {
            val username = sharedPref.getString("email", "")
            val password = sharedPref.getString("password", "")
            val token = sharedPref.getString("token", "")
            login(username.toString(), password.toString(), false)

        }

        view.singup.setOnClickListener { nextTo() }
        view.btSingIn.setOnClickListener {
            login(
                txtEmail.text.toString(),
                txtPassword.text.toString()
            )
        }

        view.singup.setOnClickListener {
            nextTo()
        }
        return view
    }

    private fun login(email: String, password: String, verify: Boolean = false) {


        if (email.isNotEmpty() && password.isNotEmpty()) {
            val dao = UserDAO()

            val userLogin = UserLogin(email, password)

            try {


                dao.login(userLogin) {
                    Log.i("user", it.toString())

                    val user = it

                    val sharedPref =
                        activity?.getSharedPreferences("user", Context.MODE_PRIVATE)

                    if (sharedPref != null) {
                        with(sharedPref.edit()) {
                            putString("email", user.email)
                            putString("password", user.password)
                            putString("token", user.token)
                            commit()
                        }
                    }

                    Log.i("tag", it.toString())
                    val intent = Intent(activity, MainActivity::class.java)
                    intent.flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                    startActivity(intent)

                }
            }catch (e: Exception){
                Toast.makeText(activity, R.string.login_field_error, Toast.LENGTH_SHORT).show()
            }
//            if (verify)
//                Toast.makeText(activity, R.string.login_field_error, Toast.LENGTH_SHORT).show()

        }


    }

    private fun nextTo() {
        findNavController().navigate(R.id.navigateToRegister)
    }

}