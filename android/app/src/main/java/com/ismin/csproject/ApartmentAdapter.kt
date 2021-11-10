package com.ismin.csproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ApartmentAdapter(private val apartments: ArrayList<Apartment>): RecyclerView.Adapter<ApartmentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApartmentViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.`row_apartment`, parent, false)
        return ApartmentViewHolder(row)
    }

    override fun onBindViewHolder(holder: ApartmentViewHolder, position: Int) {
        val (id, nom, description,zone) = apartments[position]

        holder.txvName.text = nom
        holder.txvId.text = id.toString()
        holder.txvZone.text = zone
    }

    override fun getItemCount(): Int {
        return apartments.size
    }

    fun refreshData(newApartments: ArrayList<Apartment>) {
        apartments.clear()
        apartments.addAll(newApartments)
    }
}