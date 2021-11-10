package com.ismin.csproject

import android.app.Notification
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
//import com.squareup.picasso.Picasso

class ApartmentDetails : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apartment_details)
        var Id = findViewById<TextView>(R.id.textView)

        val actionBar: ActionBar?=supportActionBar
        var intent=intent
        val dTitre=intent.getStringExtra("iNom")


        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setDisplayShowHomeEnabled(true)
            actionBar.setHomeButtonEnabled(true)
            actionBar.setTitle(dTitre)
        }

        //Picasso.get().load(dImage).into(txvImage);
        Id.text=dTitre
        //txvPeriode.text=dPeriode

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}