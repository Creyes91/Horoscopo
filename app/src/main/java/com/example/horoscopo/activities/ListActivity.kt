package com.example.horoscopo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscopo.R
import com.example.horoscopo.adapter.HoroscopeAdapter
import com.example.horoscopo.data.Horoscope
import com.example.horoscopo.data.HoroscopeProvider

class ListActivity : AppCompatActivity() {

    lateinit var horoscopeList: List<Horoscope>
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: HoroscopeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        recyclerView=findViewById(R.id.rvMain) // creacion de RV

        horoscopeList=HoroscopeProvider.findAll() //creacion asignacion de lista
        adapter = HoroscopeAdapter(horoscopeList) { position ->
            //aqui poner que hace cuaando detecta un click
            val horoscope = horoscopeList[position]
            navigateToDetail(horoscope)

        } //creacion de adapter pasando la lista

        recyclerView.adapter=adapter //asignacion del adapter creado en la activity al adapter de RV
        recyclerView.layoutManager= LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false) // manejo del RV en vertical


       // recyclerView.layoutManager= GridLayoutManager(this,2) //RV en columnas





    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.list_menu, menu)
        val itemSearch = menu?.findItem(R.id.itemSearch)!!


        return super.onCreateOptionsMenu(menu)
    }

    override fun onResume() {
        super.onResume()

        //notificar al adapter que a cambiado para refrescar la lista
        adapter.notifyDataSetChanged() //asi refresca la vista
    }



    private fun navigateToDetail(horoscope: Horoscope)
    {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("horoscope_id", horoscope.id) //envia informacion al activity destino con putExtra de la clase intent
        startActivity(intent)
    }
}