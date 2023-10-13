package com.hindu.dgniryat.ui.otherscreen.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hindu.dgniryat.databinding.ActivityInvoiceBinding

class InvoiceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInvoiceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInvoiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.downloadInv.setOnClickListener {
            Toast.makeText(this,"Invoice Downloaded", Toast.LENGTH_SHORT).show()
        }


    }

}