package com.hindu.dgniryat.ui.otherscreen.fragments.TrackingFragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.hindu.dgniryat.model.StatusModel
import com.hindu.dgniryat.R
import com.hindu.dgniryat.databinding.FragmentTrackingBinding

class TrackingFragment : Fragment() {

    private var _binding: FragmentTrackingBinding? = null
    private val binding get(): FragmentTrackingBinding = _binding!!

    private lateinit var orderId: String
    private lateinit var orderName: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTrackingBinding.inflate(inflater, container, false)


        val pref = context?.getSharedPreferences("PREFS", Context.MODE_PRIVATE)
        if (pref != null) {
            this.orderId = pref.getString("orderId", "none")!!
            this.orderName = pref.getString("articleName", "none")!!
        }

        binding.apply {

            orderNoTrack.text = "Order ID: $orderId"
            articleNameTrack.text = orderName

            detailsTV.setOnClickListener {
                val pref = context?.getSharedPreferences("PREFS", Context.MODE_PRIVATE)?.edit()
                pref?.putString("orderId", orderId)
                pref?.apply()
                Navigation.findNavController(root)
                    .navigate(R.id.action_trackingFragment_to_articleDetails)
            }

            updateStatus()

        }

        return binding.root
    }

    private fun updateStatus() {
        val dbRef = FirebaseDatabase.getInstance().reference.child("Status")
            .child(orderId)

        val blue = ContextCompat.getColor(requireContext(), R.color.blue)

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val data = snapshot.getValue(StatusModel::class.java)
                    if (data?.underProcess == 1) {
                        binding.imgUnderProcess.setImageResource(R.drawable.process_approved)
                    } else if (data?.underProcess == 2) {
                        binding.imgUnderProcess.setImageResource(R.drawable.process_rejected)
                    }

                    if (data?.dispatchDakghar == 1) {
                        binding.imgDpDakghar.setImageResource(R.drawable.process_approved)
                        binding.dpDakghar.setTextColor(blue)
                    } else if (data?.dispatchDakghar == 2) {
                        binding.imgDpDakghar.setImageResource(R.drawable.process_rejected)
                        binding.messageDpDakghar.setTextColor(Color.RED)
                    }

                    binding.messageDpDakghar.text = data?.message_dg


                    if (data?.reachedExportWH == 1) {
                        binding.imgExportWarehouse.setImageResource(R.drawable.process_approved)
                        binding.exportWarehouse.setTextColor(blue)
                    } else if (data?.reachedExportWH == 2) {
                        binding.imgExportWarehouse.setImageResource(R.drawable.process_rejected)
                        binding.exportWarehouse.setTextColor(Color.RED)
                    }

                    binding.messageExportWarehouse.text = data?.message_ew

                    if (data?.exportCustom == 1) {
                        binding.imgExportCustom.setImageResource(R.drawable.process_approved)
                        binding.exportCustom.setTextColor(blue)
                    } else if (data?.exportCustom == 2) {
                        binding.imgExportCustom.setImageResource(R.drawable.process_rejected)
                        binding.exportCustom.setTextColor(Color.RED)
                    }

                    binding.messageExportCustom.text = data?.message_ec

                    if (data?.shipWarehouse == 1) {
                        binding.imgShipWarehouse.setImageResource(R.drawable.process_approved)
                        binding.shipWarehouse.setTextColor(blue)
                    } else if (data?.shipWarehouse == 2) {
                        binding.imgShipWarehouse.setImageResource(R.drawable.process_rejected)
                        binding.shipWarehouse.setTextColor(Color.RED)
                    }

                    binding.messageShipWarehouse.text = data?.message_sw

                    if (data?.importCustom == 1) {
                        binding.imgImportCustom.setImageResource(R.drawable.process_approved)
                        binding.importCustom.setTextColor(blue)
                    } else if (data?.importCustom == 2) {
                        binding.imgImportCustom.setImageResource(R.drawable.process_rejected)
                        binding.importCustom.setTextColor(Color.RED)
                    }

                    binding.messageImportCustoms.text = data?.message_ic

                    if (data?.importWarehouse == 1) {
                        binding.imgImportWarehouse.setImageResource(R.drawable.process_approved)
                        binding.importWarehouse.setTextColor(blue)
                    } else if (data?.importWarehouse == 2) {
                        binding.imgImportWarehouse.setImageResource(R.drawable.process_rejected)
                        binding.importWarehouse.setTextColor(Color.RED)
                    }

                    binding.messageImportWarehouse.text = data?.message_iw

                    if (data?.regionalLogistics == 1) {
                        binding.imgRegionalLogistic.setImageResource(R.drawable.process_approved)
                        binding.regionalLogistic.setTextColor(blue)
                    } else if (data?.regionalLogistics == 2) {
                        binding.imgRegionalLogistic.setImageResource(R.drawable.process_rejected)
                        binding.regionalLogistic.setTextColor(Color.RED)
                    }

                    binding.messageRegLogistics.text = data?.message_rl


                    if (data?.delivered == 1) {
                        binding.imgDelivered.setImageResource(R.drawable.delivered_filled)
                        binding.delivered.setTextColor(blue)
                    } else if (data?.delivered == 2) {
                        binding.imgDelivered.setImageResource(R.drawable.process_rejected)
                        binding.delivered.setTextColor(Color.RED)
                    }


                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}