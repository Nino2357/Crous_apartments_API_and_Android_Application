package com.ismin.csproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class BookAdapter(private val books: ArrayList<Apartment>): RecyclerView.Adapter<BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.row_book, parent, false)
        return BookViewHolder(row)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val (id, nom, description,zone) = books[position]

        holder.txvTitle.text = nom
        holder.txvAuthor.text = id.toString()
        holder.txvDate.text = zone
    }

    override fun getItemCount(): Int {
        return books.size
    }

    fun refreshData(newBooks: ArrayList<Apartment>) {
        books.clear()
        books.addAll(newBooks)
    }
}