package com.example.gigr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var logoutBtn = findViewById<Button>(R.id.logout_btn)
        var emailDataOut = findViewById<TextView>(R.id.userName)

        val userEmail = intent.getStringExtra("user_email")
        emailDataOut.text =  "$userEmail"





        logoutBtn.setOnClickListener{
            Firebase.auth.signOut()
            val intent = Intent(this, login_activity::class.java)
            startActivity(intent)
            finish()
        }
    }


}