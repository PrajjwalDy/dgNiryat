package com.hindu.dgniryat.ui.otherscreen.activity

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.hindu.dgniryat.R

private val Nothing?.text: Any
    get() {
        TODO("Not yet implemented")
    }

class AdminSignup : AppCompatActivity() {

    private lateinit var adminfullName: EditText
    private lateinit var adminemailsignup: EditText
    private lateinit var adminphone: EditText
    private lateinit var adminpassword: EditText
    private lateinit var adminhaveAccount_text: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_signup)

        adminhaveAccount_text = findViewById<TextView>(R.id.adminhaveAccount_text)
        val adminsignupbutton = findViewById<Button>(R.id.adminsignUp_button)
        adminfullName = findViewById<EditText>(R.id.adminfullName_edit_text)
        adminemailsignup = findViewById<EditText>(R.id.adminemail_edit_text)
      adminphone = findViewById<EditText>(R.id.adminphone_number_text)
        adminpassword = findViewById(R.id.adminpassword_Create_AC)




        adminhaveAccount_text.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }

        adminsignupbutton.setOnClickListener {
            signUp()
        }
    }

    private fun signUp() {

        val fullName = adminfullName.text.toString().trim { it <= ' ' }
        val email = adminemailsignup.text.toString().trim { it <= ' ' }
        val phone = adminphone.text.toString().trim { it <= ' ' }
        val password = adminpassword.text.toString().trim { it <= ' ' }

        when {
            TextUtils.isEmpty(fullName) -> {
                Toast.makeText(this@AdminSignup, "Please enter your Full Name", Toast.LENGTH_SHORT)
                    .show()
            }

            TextUtils.isEmpty(email) -> {
                Toast.makeText(this@AdminSignup, "Please enter your UID", Toast.LENGTH_SHORT).show()
            }

            TextUtils.isEmpty(phone) -> {
                Toast.makeText(this@AdminSignup, "Please enter your Phone Number", Toast.LENGTH_SHORT)
                    .show()
            }

            TextUtils.isEmpty(password) -> {
                Toast.makeText(this@AdminSignup, "Please enter your password", Toast.LENGTH_SHORT)
                    .show()
            }

            else -> {
                val progressDialog = ProgressDialog(this@AdminSignup)
                progressDialog.setTitle("Registration in progress")
                progressDialog.setTitle("Please wait")
                progressDialog.setCanceledOnTouchOutside(false)
                progressDialog.show()

                val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

                mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            saveData(fullName, email, phone, password, progressDialog)
                        } else {
                            println(email)
                            val message = task.exception.toString()
                            adminhaveAccount_text.text = message
                            Toast.makeText(this, "$message", Toast.LENGTH_LONG)
                                .show()
                            mAuth.signOut()
                            progressDialog.dismiss()
                        }
                    }

            }
        }
    }

    private fun saveData(
        fullName: String,
        Email: String,
        phone: String,
        password: String,
        progressDialog: ProgressDialog
    ) {

        val currentUserID = FirebaseAuth.getInstance().currentUser!!.uid
        val firebaseUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser
        val database = Firebase.database
        val userRef = database.reference.child("Users")

        val dataMap = HashMap<String, Any>()
        dataMap["uid"] = currentUserID
        dataMap["fullName"] = fullName
        dataMap["Email"] = Email
        dataMap["password"] = password
        dataMap["phone"] = phone
        dataMap["profileImage"] = "https://firebasestorage.googleapis.com/v0/b/cunow-2fcfa.appspot.com/o/user.png?alt=media&token=af6c2872-edb2-4d9b-ac62-2b61cefc8ad1"
        dataMap["verification"] = false
        dataMap["firstVisit"] = true
        dataMap["firstVisit"] = true

        userRef.child(currentUserID).setValue(dataMap).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                progressDialog.dismiss()

                firebaseUser!!.sendEmailVerification()
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                this,
                                "Verification link send success",
                                Toast.LENGTH_LONG
                            ).show()
                            val intent = Intent(this@AdminSignup, verification::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)

                        } else {
                            Toast.makeText(
                                this,
                                "Some Error occurred or you may entered wrong UID",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }


            } else {
                val message = task.exception.toString()
                Toast.makeText(this, "Some Error Occurred: $message", Toast.LENGTH_LONG).show()
                FirebaseAuth.getInstance().signOut()
                progressDialog.dismiss()
            }

        }

    }


}