package com.hendev.logindeneme

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sp = getSharedPreferences("LoginCreds", Context.MODE_PRIVATE)
        val editor =sp.edit()

        if (sp.getBoolean("logged",false)){
            //Snackbar.make(btnLogin, "Logged in as Admin", Snackbar.LENGTH_LONG).setTextColor(Color.GREEN).show()
            Toast.makeText(this,"Already Logged",Toast.LENGTH_SHORT).show()
            finish()
            startActivity(Intent(this@MainActivity, LoggedActivity::class.java))
        }

        btnLogin.setOnClickListener {
            val id = edtUsername.text.toString()
            val pw = edtPass.text.toString()

            if (id.isNullOrEmpty() && pw.isNullOrEmpty()){
                Snackbar.make(btnLogin, "Please Type Your ID and PW", Snackbar.LENGTH_LONG).setTextColor(Color.RED).show()
                return@setOnClickListener
            }
            else{
                if (id == "admin" && pw == "12345") {
                    //Toast.makeText(this,"Login Successful",Toast.LENGTH_SHORT).show()
                    editor.putBoolean("logged",true)
                    editor.putString("username",id)
                    editor.putString("pass",pw)
                    editor.apply()
                    finish()
                    startActivity(Intent(this@MainActivity, LoggedActivity::class.java))
                } else {
                    Snackbar.make(btnLogin, "Username or Password Error", Snackbar.LENGTH_LONG).setTextColor(Color.GREEN).show()
                }
            }



        }
    }
}