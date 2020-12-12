package com.ifpr.supertrivia.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.ifpr.supertrivia.R
import com.ifpr.supertrivia.dao.UserDAO
import com.ifpr.supertrivia.model.User
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.view.*


class RegisterFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        view.btSingUp.setOnClickListener {
            register(
                txtEmail.text.toString(),
                txtPasswordRegister.text.toString(),
                txtName.text.toString(),
                txtConfirmPassword.text.toString()
            )
        }
        return view
    }

    private fun register(email: String, password: String, name: String, confirm: String) {

        val user = User(email, name, password)

        if (user.email == "" || user.password == "") {
            Toast.makeText(activity, R.string.register_field_empty, Toast.LENGTH_SHORT).show()

        } else {

            if (user.password != confirm) {
                Toast.makeText(activity, R.string.register_field_password, Toast.LENGTH_SHORT)
                    .show()
            } else {

                val dao = UserDAO()
                dao.insert(user) {}


                val build: AlertDialog.Builder = AlertDialog.Builder(activity)
                build.setTitle(R.string.register_dialog_title)
                build.setMessage(R.string.register_dialog_msg)

                build.setPositiveButton(R.string.ok) { dialog, which ->


                    // register user in database
                    findNavController().navigate(R.id.navigateToLogin)
                    dialog.dismiss()
                }

                val alertDialog: AlertDialog = build.create()
                alertDialog.show()

            }
        }


    }
}

