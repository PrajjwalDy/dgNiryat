package com.hindu.dgniryat.ui.otherscreen.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.hindu.dgniryat.R

class welcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

    val userbutton = findViewById<AppCompatButton>(R.id.user_new)
        val adminbutton = findViewById<AppCompatButton>(R.id.admin_new)

        userbutton.setOnClickListener {
           startActivity(Intent(this, Login::class.java))
            finish()
        }
        adminbutton.setOnClickListener {
            startActivity(Intent(this, AdminLogin::class.java))
            finish()
        }

    }
}