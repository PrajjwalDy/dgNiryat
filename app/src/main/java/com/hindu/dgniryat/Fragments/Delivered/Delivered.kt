package com.hindu.dgniryat.Fragments.Delivered

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hindu.dgniryat.R

class Delivered : Fragment() {

    companion object {
        fun newInstance() = Delivered()
    }

    private lateinit var viewModel: DeliveredViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_delivered, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DeliveredViewModel::class.java)
        // TODO: Use the ViewModel
    }

}