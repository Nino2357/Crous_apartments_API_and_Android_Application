package com.ismin.csproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class InfoFragment : Fragment() {
    private lateinit var apartments: ArrayList<Apartment>
    private lateinit var adapter : ApartmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val text = ""

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_info, container, false)
        return view;
    }

    companion object {
        @JvmStatic
        fun newInstance(info: String) =
            InfoFragment().apply {}
    }
}