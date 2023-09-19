package com.hindu.dgniryat.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.hindu.dgniryat.Model.UserModel
import com.hindu.dgniryat.R
import org.w3c.dom.Text

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root:View =inflater.inflate(R.layout.fragment_profile, container, false)

        val userName = root.findViewById<TextView>(R.id.userFullName)
        val email = root.findViewById<TextView>(R.id.email_user)
        val phone = root.findViewById<TextView>(R.id.userMobile)

        getData(userName,email,phone)
        return root

    }
    private fun getData(userName:TextView,email:TextView,phone:TextView){
        val dbRef = FirebaseDatabase.getInstance().reference.child("Users")
            .child(FirebaseAuth.getInstance().currentUser!!.uid)

        dbRef.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
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