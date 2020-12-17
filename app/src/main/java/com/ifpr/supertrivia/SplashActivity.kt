package com.ifpr.supertrivia

import android.app.AlertDialog
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import kotlin.system.exitProcess

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)



        Handler().postDelayed(Runnable {
            kotlin.run {
                if(isOnline()){
                    val intent = Intent(applicationContext, AcessActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }else{
                    val build: AlertDialog.Builder = AlertDialog.Builder(this)
                    build.setMessage(R.string.no_internet)
                    build.setCancelable(false)
                    build.setPositiveButton(R.string.ok) { dialog, which ->


                        finish()
                        exitProcess(0)
                        // register user in database
                        dialog.dismiss()
                    }

                    val alertDialog: AlertDialog = build.create()
                    alertDialog.show()

                }


            }
        }, 1500)

    }

    fun isOnline(): Boolean {
        val manager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        return manager.activeNetwork != null
    }
}