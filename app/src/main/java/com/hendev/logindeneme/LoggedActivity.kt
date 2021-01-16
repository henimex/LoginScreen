package com.hendev.logindeneme

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_logged.*


class LoggedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged)

        val sp = getSharedPreferences("LoginCreds",Context.MODE_PRIVATE)
        val editor = sp.edit()
        Snackbar.make(findViewById(android.R.id.content), "Logged in as ${sp.getString("username", "No Username")}", Snackbar.LENGTH_LONG).setTextColor(Color.GREEN).show()

        txtUname.text = sp.getString("username", "No Username")
        txtPw.text = sp.getString("pass","*******")

        btnLoggeddOut.setOnClickListener {
            editor.putBoolean("logged",false)
            editor.remove("username")
            editor.remove("pass")
            editor.apply()
            startActivity(Intent(this@LoggedActivity,MainActivity::class.java))
            finish()
            Toast.makeText(this,"Logged Out",Toast.LENGTH_SHORT).show()
            //Snackbar.make(btnLoggeddOut,"Logged Out",Snackbar.LENGTH_LONG).setTextColor(Color.RED).show()
        }
    }
}