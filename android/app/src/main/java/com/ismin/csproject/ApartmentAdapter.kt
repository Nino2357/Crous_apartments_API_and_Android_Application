package com.ismin.csproject

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import androidx.core.content.ContextCompat

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ApartmentAdapter(private val apartments: ArrayList<Apartment>): RecyclerView.Adapter<ApartmentViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApartmentViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.`row_apartment`, parent, false)
        context=parent.context
        return ApartmentViewHolder(row)


    }

    override fun onBindViewHolder(holder: ApartmentViewHolder, position: Int) {
        val (id, nom, description,zone) = apartments[position]
        holder.txvName.text = nom
        holder.txvId.text = id.toString()
        holder.txvZone.text = zone




        holder.itemView.setOnClickListener{
            val intent= Intent(context,ApartmentDetails::class.java)
            intent.putExtra("iNom",nom)
            intent.putExtra("iDesc",description)
            //intent.putExtra("iLegende",legende)
            //intent.putExtra("iPeriode",periode)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return apartments.size
    }

    fun refreshData(newApartments: ArrayList<Apartment>) {
        apartments.clear()
        apartments.addAll(newApartments)
    }
}