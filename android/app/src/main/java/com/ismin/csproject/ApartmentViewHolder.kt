package com.ismin.csproject

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ApartmentViewHolder(rootView: View): RecyclerView.ViewHolder(rootView) {

    var txvName = rootView.findViewById<TextView>(R.id.r_apartment_txv_name)
    var txvId = rootView.findViewById<TextView>(R.id.r_apartment_txv_id)
    var txvZone = rootView.findViewById<TextView>(R.id.r_apartment_txv_zone)
}