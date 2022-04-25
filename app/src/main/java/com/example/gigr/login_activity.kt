package com.example.gigr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class login_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        var signUpLink = findViewById<Button>(R.id.sign_up_link)

        signUpLink.setOnClickListener{
            val intent = Intent(this@login_activity, register_activity::class.java)
            startActivity(intent)
            finish()
        }
    }
}