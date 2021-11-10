package com.ismin.csproject

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class ApartmentAdapter(private val apartments: ArrayList<Apartment>): RecyclerView.Adapter<ApartmentViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApartmentViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.`row_apartment`, parent, false)
        context=parent.context
        return ApartmentViewHolder(row)


    }

    override fun onBindViewHolder(holder: ApartmentViewHolder, position: Int) {
        val (id, nom, description,zone,coordX,coordY,phone,address, photo:String?,favori:Boolean?) = apartments[position]
        holder.txvName.text = nom
        holder.txvId.text = id.toString()
        holder.txvZone.text = zone


        holder.buttonFave.setOnClickListener {
                favourite(holder,favori)
        }


        holder.itemView.setOnClickListener{
            val intent= Intent(context,ApartmentDetails::class.java)
            intent.putExtra("iNom",nom)
            intent.putExtra("iDesc",description)
            intent.putExtra("iPhone",phone)
            intent.putExtra("iAddress",address)
            if(photo != null){

                intent.putExtra("iPhotoURL",photo)
            }
            else{
                val url = "None"
                intent.putExtra("iPhotoURL",url)
            }



            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return apartments.size
    }

    fun favourite(holder: ApartmentViewHolder,favori:Boolean){
        var icon:Int
        if(favori) {
            icon=android.R.drawable.star_off
        }
        else {
            icon=android.R.drawable.star_on
        }
        holder.buttonFave.setImageDrawable(
            ContextCompat.getDrawable(context, icon));

    }
    fun refreshData(newApartments: ArrayList<Apartment>) {
        apartments.clear()
        apartments.addAll(newApartments)
    }
}