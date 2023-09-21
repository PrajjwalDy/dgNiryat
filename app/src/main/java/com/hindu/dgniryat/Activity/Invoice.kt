package com.hindu.dgniryat.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import com.hindu.dgniryat.R

class Invoice : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invoice)


        val download = findViewById<AppCompatButton>(R.id.downloadInv)

        download.setOnClickListener {
            Toast.makeText(this,"Invoice Downloaded", Toast.LENGTH_SHORT).show()
        }


    }

}