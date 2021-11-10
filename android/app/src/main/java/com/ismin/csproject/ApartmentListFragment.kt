package com.ismin.csproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val ARG_APARTMENTS = "apartments"

class ApartmentListFragment : Fragment() {
    private lateinit var apartments: ArrayList<Apartment>
    private lateinit var adapter : ApartmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val argApartments = requireArguments().getSerializable(ARG_APARTMENTS) as ArrayList<Apartment>?
        apartments = argApartments ?: ArrayList()
        adapter = ApartmentAdapter(apartments)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.`fragment_apartment_list`, container, false)

        val rcvApartments = view.findViewById<RecyclerView>(R.id.f_book_list_rcv_books)
        rcvApartments.layoutManager = LinearLayoutManager(context)
        rcvApartments.adapter = adapter

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(apartments: ArrayList<Apartment>) =
            ApartmentListFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_APARTMENTS, apartments)
                }
            }
    }
}