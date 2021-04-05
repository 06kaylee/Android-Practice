package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        var email = findViewById<EditText>(R.id.signUpEmail)
        var password = findViewById<EditText>(R.id.signUpPwd)
        var confirmPwd = findViewById<EditText>(R.id.signUpConfirmPwd)
        var signUpBtn = findViewById<Button>(R.id.signUpButton)

        signUpBtn.setOnClickListener {
            var emailMatch = android.util.Patterns.EMAIL_ADDRESS.matcher(email.text).matches()
            var pwdMatch = password.text.toString().equals(confirmPwd.text.toString())

            if(emailMatch && pwdMatch) {
                auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                    .addOnCompleteListener {
                        if(it.isSuccessful) {
                            Toast.makeText(this, "User was created!", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, MainActivity::class.java))
                        }
                        else {
                            Toast.makeText(this, "Not successful", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
            else if(!emailMatch) {
                Toast.makeText(this, "Invalid email address", Toast.LENGTH_SHORT).show()
            }
            else if(!pwdMatch) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            }

        }
    }
}