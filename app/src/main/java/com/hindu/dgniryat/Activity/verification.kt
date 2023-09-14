package com.hindu.dgniryat.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.hindu.dgniryat.R

class verification : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)
         startActivity()
    }
 val refresh_email = findViewById<Button>(R.id.refresh_email)
    private fun startActivity() {
        val firebaseUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser
        val user = FirebaseAuth.getInstance().currentUser
        refresh_email.setOnClickListener { view ->

            FirebaseAuth.getInstance().currentUser?.reload()?.addOnCompleteListener { task ->
                if (firebaseUser!!.isEmailVerified) {
                    // val intent = Intent(this@VerifyActivity, UserDetailsActivity::class.java)
                    // startActivity(intent)
                } else {
                    Snackbar.make(this, view, "Email isn't verified yet!", Snackbar.LENGTH_LONG)
                        .show()
                }
            }

        }
    }}

