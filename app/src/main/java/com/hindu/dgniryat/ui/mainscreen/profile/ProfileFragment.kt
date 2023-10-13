package com.hindu.dgniryat.ui.mainscreen.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.hindu.dgniryat.ui.otherscreen.activity.BusinessProfile
import com.hindu.dgniryat.ui.otherscreen.activity.welcome
import com.hindu.dgniryat.model.UserModel
import com.hindu.dgniryat.R

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root: View = inflater.inflate(R.layout.fragment_profile, container, false)

        val userName = root.findViewById<TextView>(R.id.userFullName)
        val email = root.findViewById<TextView>(R.id.email_user)
        val phone = root.findViewById<TextView>(R.id.userMobile)
        val businessview = root.findViewById<TextView>(R.id.waytobusiness)
        val loggingout = root.findViewById<TextView>(R.id.logout)
        getData(userName, email, phone)

        businessview.setOnClickListener {
            val intent = Intent(context, BusinessProfile::class.java)
            startActivity(intent)
        }

        loggingout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(context, welcome::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        return root
    }

    private fun getData(userName: TextView, email: TextView, phone: TextView) {
        val dbRef = FirebaseDatabase.getInstance().reference.child("Users")
            .child(FirebaseAuth.getInstance().currentUser!!.uid)

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val data = snapshot.getValue(UserModel::class.java)
                    userName.text = data?.fullName
                    email.text = data?.Email
                    phone.text = data?.phone
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })

    }


}