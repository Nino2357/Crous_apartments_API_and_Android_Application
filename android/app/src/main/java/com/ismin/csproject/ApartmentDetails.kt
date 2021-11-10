package com.ismin.csproject

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
//import com.squareup.picasso.Picasso

class ApartmentDetails : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apartment_details)
        var idNom = findViewById<TextView>(R.id.appartName)
        var idDesc = findViewById<TextView>(R.id.appartDesc)
        var idPhone = findViewById<TextView>(R.id.phoneNb)
        var idAddress = findViewById<TextView>(R.id.address)

        val actionBar: ActionBar?=supportActionBar
        var intent=intent
        val dTitre=intent.getStringExtra("iNom")
        val dDesc=intent.getStringExtra("iDesc")
        val dPhone=intent.getStringExtra("iPhone")
        val dAddress=intent.getStringExtra("iAddress")


        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setDisplayShowHomeEnabled(true)
            actionBar.setHomeButtonEnabled(true)
            actionBar.setTitle(dTitre)
        }

        idNom.text=dTitre
        idDesc.text=dDesc
        idPhone.text=dPhone
        idAddress.text=dAddress


        //val imageView: ImageView = findViewById(R.id.imageView)
        //val url = "https://cdn.pixabay.com/photo/2018/01/14/23/12/nature-3082832__340.jpg"
        //Picasso.with(this).load(url).into(imageView)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}