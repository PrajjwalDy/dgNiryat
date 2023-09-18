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
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.hindu.dgniryat.MainActivity
import com.hindu.dgniryat.R
import com.hindu.dgniryat.R.id.mail_txt
import com.hindu.dgniryat.R.id.password_txt
import com.hindu.dgniryat.databinding.ActivitySignUpBinding


private val Any.text: Any
    get() {
        TODO("Not yet implemented")
    }

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val login_btn = findViewById<Button>(R.id.login_btn)
        val create_account = findViewById<TextView>(R.id.create_account)
        val forget_password_txt = findViewById<TextView>(R.id.forget_password_txt)
        val mail_txt = findViewById<EditText>(R.id.mail_txt)
        val password_txt = findViewById<EditText>(R.id.password_txt)

        login_btn.setOnClickListener {
            login()
        }

        create_account.setOnClickListener {
            val intent = Intent(this, ActivitySignUpBinding::class.java)
            startActivity(intent)

        }

        forget_password_txt.setOnClickListener {

        }
    }

    private fun login(){
        val email = findViewById<EditText>(mail_txt).text.toString()
        val password = findViewById<EditText>(password_txt).text.toString()

        when{
            TextUtils.isEmpty(mail_txt.text.toString().trim{ it <= ' '})->{
                Toast.makeText(this@Login, "Please enter your Password", Toast.LENGTH_SHORT).show()


            }
            TextUtils.isEmpty(password_txt.text.toString().trim{ it <= ' '})->{
                Toast.makeText(this@Login, "Please enter your Password", Toast.LENGTH_SHORT).show()
            }

            else->{
                val progressDialog = ProgressDialog(this@Login)
                progressDialog.setMessage("Signing In")
                progressDialog.setCanceledOnTouchOutside(false)
                progressDialog.show()

                val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

                mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task->
                        if (task.isSuccessful){
                            val user : FirebaseUser? = FirebaseAuth.getInstance().currentUser
                            FirebaseAuth.getInstance().currentUser?.reload()?.addOnCompleteListener { task ->
                                if (user!!.isEmailVerified) {
                                    progressDialog.dismiss()
                                    val intent = Intent(this@Login, MainActivity::class.java)
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                                    startActivity(intent)
                                    Toast.makeText(this@Login, "LogIn Success", Toast.LENGTH_SHORT).show()
                                } else{
                                    progressDialog.dismiss()
                                    val intent = Intent(this@Login,verification::class.java)
                                    startActivity(intent)
                                }
                            }
                        }else{
                            val message = task.exception.toString()

                            Toast.makeText(this@Login, message, Toast.LENGTH_SHORT).show()
                            mAuth.signOut()
                            progressDialog.dismiss()
                        }
                    }
            }
        }
    }
}
