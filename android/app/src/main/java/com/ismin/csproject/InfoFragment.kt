package com.ismin.csproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment


class InfoFragment : Fragment() {
    private lateinit var apartments: ArrayList<Apartment>
    private lateinit var adapter : ApartmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_info, container, false)
        var idInfos = view.findViewById<TextView>(R.id.info)
        var idInfosTitre = view.findViewById<TextView>(R.id.info_titre)
        var idLien = view.findViewById<TextView>(R.id.lien)

        var idInfos2 = view.findViewById<TextView>(R.id.info2)

        val titre = "Crous Application"
        val text = "This project provides information on the apartments\n" +
                "offered to students by the Crous in France.\n" +
                "\n" +
                "This project is divided into 2 parts:\n" +
                "- an API part accessible at the following link :\n"
        val lien = " https://crous-project-bjy-ndn.cleverapps.io/apartments\n"
        val text2 = "- an android application part\n" +
                "\n" +
                "This application allows :\n" +
                "- to display the list of apartments\n" +
                "- to get the details of apartmens\n" +
                "- to see them on a map\n" +
                "- to mark apartment as favourite "
        idInfos.text=text
        idInfosTitre.text=titre
        idLien.text=lien
        idInfos2.text=text2

        return view;
    }

    companion object {
        @JvmStatic
        fun newInstance(info: String) =
            InfoFragment().apply {}
    }
}