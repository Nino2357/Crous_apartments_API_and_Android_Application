package com.ismin.csproject

import android.graphics.Point
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterItem
import com.google.maps.android.clustering.ClusterManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(),  OnMapReadyCallback {

    private val TAG = MainActivity::class.java.simpleName
    private lateinit var clusterManager: ClusterManager<Point>

    private val apartmentList = ApartmentList()

    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://crous-project-bjy-ndn.cleverapps.io/")
        .build()
    val bookService = retrofit.create(ApartmentService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadAllApartments()
    }

    private fun loadAllApartments() {
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
                Toast.makeText(
                    this@MainActivity,
                    "Error when trying to fetch apartments" + t.localizedMessage,
                    Toast.LENGTH_LONG
                ).show()
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
        val fragment = InfoFragment.newInstance("test info")
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
                displayMap()
                true
            }
            R.id.main_menu_info -> {
                displayInfo()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private lateinit var mMap: GoogleMap

    override fun onMapReady(gmap: GoogleMap) {
        gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(46.227638, 2.213749), 6f))
        clusterManager = ClusterManager(this, gmap)

        gmap.setOnCameraIdleListener(clusterManager)
        gmap.setOnMarkerClickListener(clusterManager)

        addAllPoint()
    }

    val supportMapFragment: SupportMapFragment = SupportMapFragment.newInstance()

    private fun displayMap() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        supportMapFragment.getMapAsync(this)
        fragmentTransaction.replace(R.id.a_main_lyt_fragment_container, supportMapFragment)
        fragmentTransaction.commit()
    }
    inner class Point(
        lat: Double,
        lng: Double,
        title: String,
        snippet: String
    ) : ClusterItem {

        private val position: LatLng
        private val title: String
        private val snippet: String

        override fun getPosition(): LatLng {
            return position
        }

        override fun getTitle(): String? {
            return title
        }
        override fun getSnippet(): String? {
            return snippet
        }
        init {
            position = LatLng(lat, lng)
            this.title = title
            this.snippet = snippet
        }
    }
    private fun addAllPoint() {
        for (apartment in apartmentList.getAllApartments()) {
            addPoint(apartment.coordX,apartment.coordY,apartment.nom,apartment.zone)
        }
    }
    private fun addPoint(lat: Double,lng: Double,title: String, snippet: String) {
        clusterManager.addItem(Point(lat, lng, title, snippet))
    }
}



