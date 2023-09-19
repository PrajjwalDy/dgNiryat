package com.hindu.dgniryat.Fragments.UnderProcess

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hindu.dgniryat.R

class UnderProcess : Fragment() {

    companion object {
        fun newInstance() = UnderProcess()
    }

    private lateinit var viewModel: UnderProcessViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_under_process, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UnderProcessViewModel::class.java)
        // TODO: Use the ViewModel
    }

}