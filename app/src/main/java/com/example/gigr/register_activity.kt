package com.example.gigr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class register_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var loginLink = findViewById<Button>(R.id.login_link)

        loginLink.setOnClickListener{
            val intent = Intent(this@register_activity, login_activity::class.java)
            startActivity(intent)
            finish()
        }

    }
}