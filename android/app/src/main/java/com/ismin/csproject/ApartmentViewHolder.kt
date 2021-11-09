package com.ismin.csproject

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ApartmentViewHolder(rootView: View): RecyclerView.ViewHolder(rootView) {

    var txvName = rootView.findViewById<TextView>(R.id.r_book_txv_title)
    var txvId = rootView.findViewById<TextView>(R.id.r_book_txv_author)
    var txvZone = rootView.findViewById<TextView>(R.id.r_book_txv_date)
}