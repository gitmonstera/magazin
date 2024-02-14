package com.example.magazin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class RegActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)

        val userLogin: EditText = findViewById(R.id.auth_login_user)
        val userPass: EditText = findViewById(R.id.auth_passwd_user)
        val btn: Button = findViewById(R.id.button)
        val linkToReg: TextView = findViewById(R.id.auth_link_to_reg)

        linkToReg.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btn.setOnClickListener {
            val login = userLogin.text.toString().trim()
            val passwd = userPass.text.toString().trim()

            if (login == "" || passwd == "") {
                Toast.makeText(this, "Не все данные введены", Toast.LENGTH_LONG).show()
            } else {
                val user = User(login, passwd)
                val db = DbHelper(this, null)

                db.addUser(user)
                Toast.makeText(this, "Все ОК", Toast.LENGTH_LONG).show()
            }
        }
    }
}