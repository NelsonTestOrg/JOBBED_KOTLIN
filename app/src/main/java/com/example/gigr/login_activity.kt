package com.example.gigr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class login_activity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        var signUpLink = findViewById<Button>(R.id.sign_up_link)
        var login_button = findViewById<Button>(R.id.login_button)
        var userLoginEmail = findViewById<TextInputEditText>(R.id.login_email)
        var userLoginPassword = findViewById<TextInputEditText>(R.id.login_password)



        signUpLink.setOnClickListener{
            val intent = Intent(this@login_activity, register_activity::class.java)
            startActivity(intent)
            finish()
        }

        login_button.setOnClickListener {
            when {
                TextUtils.isEmpty(userLoginEmail.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this,
                        "Enter Email to log in",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(userLoginPassword.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this,
                        "Enter a Password.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {
                    val loginemail: String = userLoginEmail.text.toString().trim { it <= ' ' }
                    val loginpassword: String = userLoginPassword.text.toString().trim { it <= ' ' }

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(loginemail, loginpassword)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {

                                Toast.makeText(
                                    this,
                                    "Login successful.",
                                    Toast.LENGTH_SHORT
                                ).show()

                                val intent = Intent(this, MainActivity::class.java)

                                intent.flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.putExtra("user_email", loginemail)
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

                }

            }


        }

   }
//    override fun onStart() {
//        auth = Firebase.auth
//        super.onStart()
//        // Check if user is signed in (non-null) and update UI accordingly.
//        val currentUser = auth.currentUser
//        updateUI(currentUser)
//    }
//
//    private fun updateUI(currentUser: FirebaseUser?) {
//        val intent = Intent(this, MainActivity::class.java)
//        startActivity(intent)
//    }
}