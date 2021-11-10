package com.ismin.csproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.ActionBar
//import com.squareup.picasso.Picasso

class ApartmentDetails : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apartment_details)
        var idNom = findViewById<TextView>(R.id.appartName)
        var idDesc = findViewById<TextView>(R.id.appartDesc)
        var idPhone = findViewById<TextView>(R.id.phoneNb)

        val actionBar: ActionBar?=supportActionBar
        var intent=intent
        val dTitre=intent.getStringExtra("iNom")
        val dDesc=intent.getStringExtra("iDesc")
        val dPhone=intent.getStringExtra("iPhone")


        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setDisplayShowHomeEnabled(true)
            actionBar.setHomeButtonEnabled(true)
            actionBar.setTitle(dTitre)
        }

        //Picasso.get().load(dImage).into(txvImage);
        idNom.text=dTitre
        idDesc.text=dDesc
        idPhone.text=dPhone

        //txvPeriode.text=dPeriode

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}