package com.ismin.csproject

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

    private val apartmentList = ApartmentList()
    private lateinit var btnCreateBook: FloatingActionButton

    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://crous-project-bjy-ndn.cleverapps.io/")
        .build()
    val bookService = retrofit.create(ApartmentService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadAllBooks()
    }

    private fun loadAllBooks() {
        bookService.getAllApartments().enqueue(object : Callback<List<Apartment>> {
            override fun onResponse(
                call: Call<List<Apartment>>,
                response: Response<List<Apartment>>
            ) {
                val allBooks: List<Apartment>? = response.body()

                allBooks?.forEach {
                    apartmentList.addBook(it)
                }
                displayApartmentList();
            }

            override fun onFailure(call: Call<List<Apartment>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error when trying to fetch books" + t.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }
        )
    }

    private fun displayApartmentList() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragment = ApartmentListFragment.newInstance(apartmentList.getAllApartments())
        fragmentTransaction.replace(R.id.a_main_lyt_fragment_container, fragment)
        fragmentTransaction.commit()
    }

    private fun displayInfo() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragment = InfoFragment.newInstance("test")
        fragmentTransaction.replace(R.id.a_main_lyt_fragment_container, fragment)
        fragmentTransaction.commit()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.main_menu_list -> {
                displayApartmentList()
                true
            }
            R.id.main_menu_map -> {
                displayApartmentList()
                true
            }
            R.id.main_menu_info -> {
                displayInfo()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}