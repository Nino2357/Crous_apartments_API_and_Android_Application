package com.ismin.csproject

class ApartmentList {

    private val storage = HashMap<String, Apartment>()

    fun addBook(aBook: Apartment) {
        storage[aBook.nom] = aBook
    }

    fun getBook(title: String): Apartment? {
        return storage[title]
    }

    fun getAllApartments(): ArrayList<Apartment> {
        return ArrayList(storage.values.sortedBy { it.id })
    }

    fun getBooksOf(author: String): List<Apartment> {
        return storage
            .filterValues { it.nom == author }
            .values
            .sortedBy { it.id }
            .toList();
    }

    fun getTotalNumberOfBooks(): Int {
        return storage.size;
    }

    fun clear() {
        storage.clear()
    }

}
