package com.hindu.dgniryat.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import com.hindu.dgniryat.R
class BookArticle : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root:View =  inflater.inflate(R.layout.fragment_book_article, container, false)

        val proceedbtn1 = root.findViewById<AppCompatButton>(R.id.proceedBtn1)
        val proceedbtn2 = root.findViewById<AppCompatButton>(R.id.proceedBtn2)






        return root
    }
}