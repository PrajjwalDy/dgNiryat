package com.hindu.dgniryat.Activity

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.hindu.dgniryat.MainActivity
import com.hindu.dgniryat.R
import com.hindu.dgniryat.R.*


class AdminLogin : AppCompatActivity() {
    private lateinit var adminemail: EditText
    private lateinit var adminpassword: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_admin_login)

        val adminlogin_bttn = findViewById<Button>(id.adminlogin_btn)
        val admincreate_account = findViewById<TextView>(id.admincreate_account)
        val adminforget_password = findViewById<TextView>(id.adminforget_password_txt)
        adminemail = findViewById<EditText>(id.adminmail_txt)
        adminpassword = findViewById<EditText>(id.adminpasswordsignin_txt)

        adminlogin_bttn.setOnClickListener {
            AdminLogin()
        }
        admincreate_account.setOnClickListener {
            val intent = Intent(this, AdminSignup::class.java)
            startActivity(intent)
        }
        adminforget_password.setOnClickListener {
        }
    }

    private fun adminLogin() {
        val adminemail = adminemail.text.toString().trim { it <= ' ' }
        val adminpassword = adminpassword.text.toString().trim { it <= ' ' }

        when {
            TextUtils.isEmpty(adminemail) -> {
                Toast.makeText(this@AdminLogin, "Please enter your Password", Toast.LENGTH_SHORT)
                    .show()


            }

            TextUtils.isEmpty(adminpassword) -> {
                Toast.makeText(this@AdminLogin, "Please enter your Password", Toast.LENGTH_SHORT)
                    .show()
            }

            else -> {
                val progressDialog = ProgressDialog(this@AdminLogin)
                progressDialog.setMessage("Signing In")
                progressDialog.setCanceledOnTouchOutside(false)
                progressDialog.show()

                val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

                mAuth.signInWithEmailAndPassword(adminemail, adminpassword)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val user: FirebaseUser? = FirebaseAuth.getInstance().currentUser
                            FirebaseAuth.getInstance().currentUser?.reload()
                                ?.addOnCompleteListener { task ->
                                    if (user!!.isEmailVerified) {
                                        progressDialog.dismiss()
                                        val intent =
                                            Intent(this@AdminLogin, MainActivity::class.java)
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                                        startActivity(intent)
                                        Toast.makeText(
                                            this@AdminLogin,
                                            "LogIn Success",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } else {
                                        progressDialog.dismiss()
                                        val intent =
                                            Intent(this@AdminLogin, verification::class.java)
                                        startActivity(intent)
                                    }
                                }
                        } else {
                            val message = task.exception.toString()

                            Toast.makeText(this@AdminLogin, message, Toast.LENGTH_SHORT).show()
                            mAuth.signOut()
                            progressDialog.dismiss()
                        }
                    }
            }
        }
    }


    override fun onStart() {
        super.onStart()

        val user: FirebaseUser? = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}