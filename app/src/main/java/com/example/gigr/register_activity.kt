package com.example.gigr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class register_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var loginLink = findViewById<Button>(R.id.login_link)
        var registerButton = findViewById<Button>(R.id.register_button)
        var userEmail = findViewById<TextInputEditText>(R.id.register_email)
        var userPassword = findViewById<TextInputEditText>(R.id.register_password)
        var userConfirmPassword = findViewById<TextInputEditText>(R.id.confirm_register_password)

        loginLink.setOnClickListener{
            val intent = Intent(this@register_activity, login_activity::class.java)
            startActivity(intent)

        }

        registerButton.setOnClickListener {

            when {
                TextUtils.isEmpty(userEmail.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this,
                        "Enter Email",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(userPassword.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this,
                        "Enter a Password.",
                        Toast.LENGTH_SHORT
                    ).show()
                }


                else -> {
                    val email: String = userEmail.text.toString().trim { it <= ' ' }
                    val password: String = userPassword.text.toString().trim { it <= ' ' }

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(
                            OnCompleteListener<AuthResult> { task ->
                                if (task.isSuccessful) {

                                    val firebaseUser: FirebaseUser = task.result!!.user!!
                                    Toast.makeText(
                                        this,
                                        "Registration successful.",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    val intent =
                                        Intent(this, MainActivity::class.java)
                                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("user_id", firebaseUser.uid)
                                    intent.putExtra("user_email", email)
                                    startActivity(intent)
                                    finish()


                                } else {
                                    Toast.makeText(
                                        this,
                                        task.exception!!.message.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                            }
                        )

                }

            }
        }

    }
}