package com.hindu.dgniryat.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.hindu.dgniryat.Model.ArticleModel
import com.hindu.dgniryat.R

class ConsignmentAdapter(
    private val mContext: Context,
    private val mArticle: List<ArticleModel>
) : RecyclerView.Adapter<ConsignmentAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val orderName: TextView = itemView.findViewById(R.id.orderName)
        private val country: TextView = itemView.findViewById(R.id.toCounty_layout)
        private val statusImage: ImageView = itemView.findViewById(R.id.statusImage)
        private val status: TextView = itemView.findViewById(R.id.status)
        private val productType: TextView = itemView.findViewById(R.id.productType_layout)


        fun bind(list: ArticleModel) {
            orderName.text = list.orderName
            country.text = list.rCountry
            productType.text = list.productType
            status.text = list.status
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(mContext).inflate(R.layout.consignment_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mArticle.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mArticle[position])

        holder.itemView.setOnClickListener {
            val pref = mContext.getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit()
            pref.putString("orderId", mArticle[position].orderId)
            pref.putString("articleName", mArticle[position].orderName)
            pref.apply()
            Navigation.findNavController(holder.itemView)
                .navigate(R.id.action_navigation_dashboard_to_trackingFragment)
        }

    }

}